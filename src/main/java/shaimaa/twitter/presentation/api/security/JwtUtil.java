package shaimaa.twitter.presentation.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
@Getter
@Setter
class JwtConfig {
    private String secretKey;
    private String issuer;
    private long accessTokenTtlInMinutes;
    private long refreshTokenTtlInMinutes;
}

@Component
class JwtUtil {
    @Autowired
    private JwtConfig jwtConfig;

    String getSubject(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        Key key = new SecretKeySpec(Base64.getDecoder().decode(jwtConfig.getSecretKey()),
                SignatureAlgorithm.HS256.getJcaName());

            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody();
    }
}
