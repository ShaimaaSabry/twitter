import { check, sleep } from 'k6'
import http, { Response } from 'k6/http'

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080'

export const options = {
    vus: 100,
    duration: '1m',
    thresholds: {
        checks: ['rate==1.0'],        // 100% of your check() assertions must pass
        http_req_failed: ['rate==0'], // 0% request-level failures (no 4xx/5xx/network errors)
        http_req_duration: [
            'p(95)<40',  // 95th percentile < 40 ms
            'max<200'    // max < 200 ms
        ]
    },
}

export default function() {
    const url = `${BASE_URL}/v1/tweets/:timeline`
    const params = {
        headers: {
            'Content-Type': 'application/json',
            'X-User-Id': '1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c',
        }
    }
    const response = http.get(url, params)

    const isOk = check(response, {
        'status is 200': (r) => r.status === 200,
    })

    if(!isOk) {
        console.log(`❌ Request failed with status ${response.status}: ${response.body}`)
    }
}