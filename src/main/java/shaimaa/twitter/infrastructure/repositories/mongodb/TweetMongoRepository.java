package shaimaa.twitter.infrastructure.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TweetMongoRepository extends MongoRepository<TweetMongoDocument, UUID> {
    List<TweetMongoDocument> findByContentLike(String searchTerm);
    List<TweetMongoDocument> findTop2ByUserIdOrderByCreatedAtDesc(UUID userId);
    List<TweetMongoDocument> findByUserIdInOrderByCreatedAtDesc(Set<UUID> userIds);
}
