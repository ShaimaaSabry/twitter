package shaimaa.twitter.infrastructure.repositories.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

interface UserElasticRepository extends ElasticsearchRepository<UserElasticDocument, UUID> {
}
