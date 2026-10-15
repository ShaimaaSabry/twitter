package shaimaa.twitter.entrypoint.listeners;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import shaimaa.twitter.application.commands.FanOutTweet.FanOutTweetCommand;
import shaimaa.twitter.application.commands.FanOutTweet.FanOutTweetHandler;
import shaimaa.twitter.domain.events.TweetCreatedEvent;

@Component
class TweetListener {
    private final FanOutTweetHandler fanOutTweetHandler;

    TweetListener(FanOutTweetHandler fanOutTweetHandler) {
        this.fanOutTweetHandler = fanOutTweetHandler;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
    public void onTweetCreated(TweetCreatedEvent event) {
        this.fanOutTweetHandler.handle(new FanOutTweetCommand(event.tweet()));
    }
}
