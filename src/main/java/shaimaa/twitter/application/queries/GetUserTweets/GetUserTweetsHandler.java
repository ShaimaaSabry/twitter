package shaimaa.twitter.application.queries.GetUserTweets;

import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.contracts.TweetRepository;
import shaimaa.twitter.domain.model.Tweet;

import java.util.List;
import java.util.UUID;

@Component
public class GetUserTweetsHandler {
    private final TweetRepository tweetRepository;

    public GetUserTweetsHandler(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public List<Tweet> handle(UUID userId) {
       return this.tweetRepository.getTweetsByUserId(userId, 10);
    }
}
