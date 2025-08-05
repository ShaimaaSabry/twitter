package shaimaa.twitter.infrastructure.repositories.mongodb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.model.User;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Document("tweets")
public class TweetMongoDocument {
    private UUID id;
    private UUID userId;
    private Instant createdAt;
    private String content;

    static TweetMongoDocument from(Tweet tweet) {
        return new TweetMongoDocument(
                tweet.getId(),
                tweet.getUser().getId(),
                tweet.getCreatedAt().toInstant(),
                tweet.getContent()
        );
    }

    Tweet toDomain() {
        return Tweet.of(
                this.id,
                User.of(this.userId),
                this.createdAt.atZone(ZoneId.systemDefault()),
                this.content
        );
    }
}
