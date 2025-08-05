package shaimaa.twitter.infrastructure.repositories.sql.tweet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shaimaa.twitter.domain.contracts.TweetRepository;
import shaimaa.twitter.domain.model.Tweet;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Component
public class TweetSqlRepository implements TweetRepository {
    private static final Logger logger = LoggerFactory.getLogger(TweetSqlRepository.class);

    private final TweetJpaRepository tweetJpaRepository;

public TweetSqlRepository(final TweetJpaRepository tweetJpaRepository) {
        this.tweetJpaRepository = tweetJpaRepository;
    }

    @Override
    public Tweet create(Tweet tweet) {
        TweetEntity tweetEntity = TweetEntity.from(tweet);
        tweetEntity = tweetJpaRepository.save(tweetEntity);
        return tweetEntity.toDomain();
    }

    @Override
    public List<Tweet> getTweetsByUserId(UUID userId, int limit) {
        List<TweetEntity> tweetEntities = tweetJpaRepository.findByUserIdOrderByCreatedAtDesc(
                userId,
                PageRequest.of(0, limit)
        );

        return tweetEntities
                .stream()
                .map(TweetEntity::toDomain)
                .toList();
    }

    @Override
    public List<Tweet> search(String q, int limit) {
        List<TweetEntity> tweetEntities = tweetJpaRepository.searchByContentContainingIgnoreCase(
                q,
                PageRequest.of(0, limit)
        );

        return tweetEntities
                .stream()
                .map(TweetEntity::toDomain)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Tweet> generateTimeline(UUID userId, int limit) {
        List<TweetEntity> tweetEntities = tweetJpaRepository.generateTimeline(userId, limit);
        logger.debug(
                "Generated timeline for user {}: {}",
                userId,
                tweetEntities
        );

        return tweetEntities
                .stream()
                .map(TweetEntity::toDomain)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Tweet> getTimeline(UUID userId) {
        List<TweetEntity> tweetEntities = tweetJpaRepository.getTimeline(userId);

        return tweetEntities
                .stream()
                .map(TweetEntity::toDomain)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @Transactional
    public void saveTimeline(UUID userId, List<Tweet> tweets) {
        logger.debug(
                "Saving timeline for user {} with tweets {}",
                userId, tweets
        );

        UUID[] tweetIds = tweets
                .stream()
                .map(Tweet::getId)
                .toArray(UUID[]::new);

//        this.tweetJpaRepository.replaceTimeline(userId, tweetIds);

        tweetJpaRepository.deleteTimeline(userId);
        tweetJpaRepository.insertTimeline(userId, tweetIds);
    }
}
