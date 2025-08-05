package shaimaa.twitter.application.eventHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.model.User;
import shaimaa.twitter.domain.contracts.TweetRepository;
import shaimaa.twitter.domain.contracts.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class TweetCreatedHandler {
    private static final int TIMELINE_LIMIT = 5;
    private static final Logger LOGGER = LoggerFactory.getLogger(TweetCreatedHandler.class);

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    TweetCreatedHandler(UserRepository userRepository, TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public void handle(Tweet tweet) {
        LOGGER.debug(
                "Handling tweet created event for tweet: {}",
                tweet
        );

        Set<User> followers = this.userRepository.getFollowers(tweet.getUser().getId());

        for (User follower : followers) {
            List<Tweet> timeline = this.tweetRepository.getTimeline(follower.getId());

            timeline.add(tweet);

            List<Tweet> limitedTimeline = timeline.stream()
                    .sorted(Comparator.comparing(Tweet::getCreatedAt).reversed())
                    .limit(TIMELINE_LIMIT)
                    .toList();

            this.tweetRepository.saveTimeline(follower.getId(), limitedTimeline);
        }
    }
}
