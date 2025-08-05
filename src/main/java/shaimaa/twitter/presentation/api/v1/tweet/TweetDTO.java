package shaimaa.twitter.presentation.api.v1.tweet;

import shaimaa.twitter.domain.model.Tweet;

import java.time.ZonedDateTime;

record TweetDTO (
    String id,
    UserDto author,
    String content,
    ZonedDateTime createdAt
) {
    static TweetDTO from(Tweet tweet) {
        return new TweetDTO(
            tweet.getId().toString(),
            tweet.getUser() == null ? null : UserDto.from(tweet.getUser()),
            tweet.getContent(),
            tweet.getCreatedAt()
        );
    }
}
