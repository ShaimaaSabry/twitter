package shaimaa.twitter.application.commands.CreateTweet;

import java.util.UUID;

public record CreateTweetCommand(
        UUID userId,
        String content
) {
}
