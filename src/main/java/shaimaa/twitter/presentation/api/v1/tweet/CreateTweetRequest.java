package shaimaa.twitter.presentation.api.v1.tweet;

import shaimaa.twitter.application.commands.CreateTweet.CreateTweetCommand;

import java.util.UUID;

record CreateTweetRequest(
    String content
) {
    CreateTweetCommand toCommand(UUID userId) {
        return new CreateTweetCommand(userId, content);
    }
}
