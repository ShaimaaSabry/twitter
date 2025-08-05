package shaimaa.twitter.infrastructure.repositories.elastic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.contracts.TweetRepository;
import shaimaa.twitter.domain.model.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Primary
@Component
public class TweetElasticRepositoryImpl implements TweetRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TweetElasticRepositoryImpl.class);

    private final UserElasticRepository userElasticRepository;
    private final TweetElasticRepository tweetElasticRepository;

public TweetElasticRepositoryImpl(
    @Lazy final UserElasticRepository userElasticRepository,
        @Lazy final TweetElasticRepository tweetElasticRepository
) {
    this.userElasticRepository = userElasticRepository;
        this.tweetElasticRepository = tweetElasticRepository;
    }

    @Override
    public Tweet create(Tweet tweet) {
    UserElasticDocument userElasticDocument = userElasticRepository.findById(tweet.getUser().getId())
            .orElseThrow(
                () -> new IllegalArgumentException("User not found: " + tweet.getUser().getId())
            );

        TweetElasticDocument tweetElasticDocument = TweetElasticDocument.from(tweet, userElasticDocument);
        tweetElasticDocument = this.tweetElasticRepository.save(tweetElasticDocument);
        LOGGER.debug(
                "Created tweet: {}",
                tweetElasticDocument
        );
        return tweetElasticDocument.toDomain();
    }

    @Override
    public List<Tweet> getTweetsByUserId(UUID userId, int limit) {
        List<TweetElasticDocument> documents = tweetElasticRepository.findByUserId(userId, PageRequest.of(0, limit));
        return documents.stream()
                .map(TweetElasticDocument::toDomain)
                .toList();
    }

    @Override
    public List<Tweet> search(String searchTerm, int limit) {
    List<TweetElasticDocument> tweetElasticEntities = this.tweetElasticRepository.findByContentFuzzy(searchTerm);

        return tweetElasticEntities.stream()
                .map(TweetElasticDocument::toDomain)
                .toList();
    }

    @Override
    public List<Tweet> generateTimeline(UUID userId, int limit) {
        UserElasticDocument followerUser = userElasticRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        List<UUID> followingUserIds = new ArrayList<>(followerUser.getFollowing());
        LOGGER.debug(
                "User {} is following {} users: {}",
                userId,
                followingUserIds.size(),
                followingUserIds
        );
        if (followingUserIds.isEmpty()) {
            return List.of();  // No followed users -> empty timeline
        }

        List<TweetElasticDocument> tweets = tweetElasticRepository.findByUserIdInOrderByCreatedAtDesc(
                followingUserIds,
                PageRequest.of(0, limit)
        );

        return tweets.stream()
                .map(TweetElasticDocument::toDomain)
                .toList();
    }

    @Override
    public List<Tweet> getTimeline(UUID userId) {
        UserElasticDocument userElasticDocument = userElasticRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        List<Tweet> timeline = userElasticDocument.getTimeline();
        if (timeline == null) {
            return new ArrayList<>();
        }
        return timeline;
    }

    @Override
    public void saveTimeline(UUID userId, List<Tweet> tweets) {
        UserElasticDocument userElasticDocument = userElasticRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        userElasticDocument.setTimeline(tweets);

        userElasticRepository.save(userElasticDocument);
    }
}
