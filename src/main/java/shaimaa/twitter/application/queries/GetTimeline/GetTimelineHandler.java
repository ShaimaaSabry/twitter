package shaimaa.twitter.application.queries.GetTimeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.contracts.TweetRepository;
import shaimaa.twitter.domain.contracts.UserRepository;
import shaimaa.twitter.domain.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class GetTimelineHandler {
    private static final int TIMELINE_LIMIT = 5;
    private final Logger logger = LoggerFactory.getLogger(GetTimelineHandler.class);

    private final TweetRepository tweetRepository;

    GetTimelineHandler(
            TweetRepository tweetRepository
    ) {
        this.tweetRepository = tweetRepository;
    }

    public List<Tweet> handle(UUID userId) {
        List<Tweet> tweets = this.tweetRepository.getTimeline(userId);

        if(tweets.size() < TIMELINE_LIMIT) {
            // If the timeline is not full, generate it
//            tweets = this.generateTimeline(userId);
        }

        return tweets;
    }

    private List<Tweet> generateTimeline(UUID userId) {
        List<Tweet> tweets = this.tweetRepository.generateTimeline(userId, TIMELINE_LIMIT);
        logger.debug(
                "Generated timeline for user {}: {}",
                userId,
                tweets
        );

        this.tweetRepository.saveTimeline(userId, tweets);

        return tweets;
    }
}
