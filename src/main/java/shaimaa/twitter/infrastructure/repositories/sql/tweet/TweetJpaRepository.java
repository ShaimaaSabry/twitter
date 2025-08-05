package shaimaa.twitter.infrastructure.repositories.sql.tweet;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
interface TweetJpaRepository extends CrudRepository<TweetEntity, UUID> {
    List<TweetEntity> findByUserIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);

    List<TweetEntity> searchByContentContainingIgnoreCase(String q, Pageable pageable);

    @Query(value = """
          SELECT t.*
          FROM tweets t
          WHERE t.content ILIKE CONCAT('%', :q, '%')
          ORDER BY t.created_at DESC
          LIMIT :limit
    """, nativeQuery = true)
    List<TweetEntity> searchTweets(String q, int limit);

    @Query(value = """
          SELECT t.*
          FROM user_follow uf
          JOIN tweets t ON uf.followed_id = t.user_id
          WHERE uf.follower_id = :followerId
          ORDER BY t.created_at DESC
          LIMIT :limit
    """, nativeQuery = true)
    List<TweetEntity> generateTimeline(UUID followerId, int limit);

    @Query(value = """
        SELECT t.*
        FROM tweets t
        JOIN timeline tl ON t.id = tl.tweet_id
        WHERE tl.user_id = :userId
        ORDER BY t.created_at DESC
    """, nativeQuery = true)
    List<TweetEntity> getTimeline(UUID userId);

    @Modifying
    @Transactional
    @Query(value = """
        WITH deleted AS (
            DELETE FROM timeline WHERE user_id = :userId
        )
        INSERT INTO timeline (user_id, tweet_id)
        SELECT :userId, unnest(:tweetIds)
        ON CONFLICT (user_id, tweet_id) DO NOTHING
        """, nativeQuery = true)
    void replaceTimeline(UUID userId, UUID[] tweetIds);

    @Modifying
    @Query(
            value = "DELETE FROM timeline t WHERE t.user_id = :userId",
            nativeQuery = true
    )
    void deleteTimeline(UUID userId);

    @Modifying
    @Query(value = """
        INSERT INTO timeline (user_id, tweet_id)
        SELECT :userId, unnest(:tweetIds)
        ON CONFLICT DO NOTHING
        """, nativeQuery = true)
    void insertTimeline(UUID userId, UUID[] tweetIds);
}
