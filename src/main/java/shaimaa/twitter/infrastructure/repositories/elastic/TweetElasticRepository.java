package shaimaa.twitter.infrastructure.repositories.elastic;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
interface TweetElasticRepository extends ElasticsearchRepository<TweetElasticDocument, UUID> {

    @Query("""
    {
      "bool": {
        "filter": {
          "term": {
            "user.id": "?0"
          }
        }
      }
    }
    """)
    List<TweetElasticDocument> findByUserId(UUID userId, Pageable pageable);

    List<TweetElasticDocument> findByContentContaining(String keyword);


    @Query("""
    {
      "fuzzy": {
        "content": {
          "value": "?0",
          "fuzziness": "AUTO"
        }
      }
    }
    """)
    List<TweetElasticDocument> findByContentFuzzy(String keyword);

    List<TweetElasticDocument> findByUserIdInOrderByCreatedAtDesc(List<UUID> userIds, Pageable pageable);
}
