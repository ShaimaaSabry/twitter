package shaimaa.twitter.infrastructure.repositories.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.contracts.TweetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//@Primary
@Component
class TweetMongoRepositoryImpl implements TweetRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TweetMongoRepositoryImpl.class);

    private final UserMongoRepository userMongoRepository;
    private final TweetMongoRepository tweetMongoRepository;

    public TweetMongoRepositoryImpl(
    @Lazy UserMongoRepository userMongoRepository,
            @Lazy TweetMongoRepository tweetMongoRepository
    ) {
        this.userMongoRepository = userMongoRepository;
        this.tweetMongoRepository = tweetMongoRepository;
    }

    public Tweet create(Tweet tweet) {
        TweetMongoDocument tweetMongoDocument = TweetMongoDocument.from(tweet);
        tweetMongoRepository.save(tweetMongoDocument);

        return tweet;
    }

    @Override
    public List<Tweet> getTweetsByUserId(UUID userId, int limit) {
        List<TweetMongoDocument> tweetEntities = tweetMongoRepository.findTop2ByUserIdOrderByCreatedAtDesc(userId);

        return tweetEntities
                .stream()
                .map(TweetMongoDocument::toDomain)
                .toList();
    }

    public List<Tweet> search(String searchTerm, int limit) {
        List<TweetMongoDocument> tweetEntities = tweetMongoRepository.findByContentLike(searchTerm);

        return tweetEntities
                .stream()
                .map(TweetMongoDocument::toDomain)
                .toList();
    }

    @Override
    public List<Tweet> generateTimeline(UUID userId, int limit) {
        UserMongoDocument userMongoDocument = userMongoRepository.findById(userId.toString())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        LOGGER.debug(
                "Generating timeline for user: {}, following: {}",
                userId, userMongoDocument.getFollowing()
        );

        List<TweetMongoDocument> tweetMongoDocumentList = tweetMongoRepository.findByUserIdInOrderByCreatedAtDesc(
                userMongoDocument.getFollowing()
        );

        return tweetMongoDocumentList
                .stream()
                .map(TweetMongoDocument::toDomain)
                .limit(limit)
                .toList();
    }

    @Override
    public List<Tweet> getTimeline(UUID userId) {
        UserMongoDocument userMongoDocument = userMongoRepository.findById(userId.toString())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        return userMongoDocument.getTimeline()
                .stream()
                .map(UserMongoDocument.TimelineTweet::toDomain)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void saveTimeline(UUID userId, List<Tweet> tweets) {
        UserMongoDocument userMongoDocument = userMongoRepository.findById(userId.toString())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        userMongoDocument.setTimeline(
                tweets
                        .stream()
                        .map(UserMongoDocument.TimelineTweet::from)
                        .toList()
        );

        userMongoRepository.save(userMongoDocument);
    }
}
