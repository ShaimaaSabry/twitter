package shaimaa.twitter.application.commands.FanOutTweet;

import shaimaa.twitter.domain.model.Tweet;

public record FanOutTweetCommand(
        Tweet tweet
) {
}
