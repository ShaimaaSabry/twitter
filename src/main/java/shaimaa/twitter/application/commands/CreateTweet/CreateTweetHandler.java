package shaimaa.twitter.application.commands.CreateTweet;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.events.TweetCreatedEvent;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.contracts.TweetRepository;

@Component
public class CreateTweetHandler {
    private final TweetRepository tweetRepository;
    private final ApplicationEventPublisher eventPublisher;

    CreateTweetHandler(
            TweetRepository tweetRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.tweetRepository = tweetRepository;
        this.eventPublisher = eventPublisher;
    }

    public Tweet handle(CreateTweetCommand command) {
        Tweet tweet = Tweet.newTweet(
                command.userId(),
                command.content()
        );

        tweet = this.tweetRepository.create(tweet);

        this.eventPublisher.publishEvent(new TweetCreatedEvent(tweet));

        return tweet;
    }
}
