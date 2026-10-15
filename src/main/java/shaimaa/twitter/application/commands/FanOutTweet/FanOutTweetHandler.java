package shaimaa.twitter.application.commands.FanOutTweet;

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

@Component
public class FanOutTweetHandler {
    private static final int TIMELINE_LIMIT = 5;
    private static final Logger LOGGER = LoggerFactory.getLogger(FanOutTweetHandler.class);

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    FanOutTweetHandler(UserRepository userRepository, TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public void handle(FanOutTweetCommand command) {
        Tweet tweet = command.tweet();

        LOGGER.debug(
                "Fanning out tweet to followers' timelines: {}",
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
