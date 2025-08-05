package shaimaa.twitter.application.commands.CreateTweet;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import shaimaa.twitter.application.eventHandlers.TweetCreatedHandler;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.contracts.TweetRepository;

@Component
public class CreateTweetHandler {
    private final TweetRepository tweetRepository;
    private final TweetCreatedHandler tweetCreatedHandler;

    CreateTweetHandler(
            TweetRepository tweetRepository,
            TweetCreatedHandler tweetCreatedHandler
    ) {
        this.tweetRepository = tweetRepository;
        this.tweetCreatedHandler = tweetCreatedHandler;
    }

    public Tweet handle(CreateTweetCommand command) {
        Tweet tweet = Tweet.newTweet(
                command.userId(),
                command.content()
        );

        tweet = this.tweetRepository.create(tweet);

        this.tweetCreatedHandler.handle(tweet);

        return tweet;
    }
}
