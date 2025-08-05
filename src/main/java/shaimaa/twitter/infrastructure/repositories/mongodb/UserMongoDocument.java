package shaimaa.twitter.infrastructure.repositories.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.model.User;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Document("users")
class UserMongoDocument {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Set<UUID> following = new HashSet<>();

    private Set<UUID> followers = new HashSet<>();

    private List<TimelineTweet> timeline = new ArrayList<>();

    @Data
    @AllArgsConstructor
    static class TimelineTweet {
        private UUID id;
        private String content;
        private Instant createdAt;

        static TimelineTweet from(Tweet tweet) {
            return new TimelineTweet(
                    tweet.getId(),
                    tweet.getContent(),
                    tweet.getCreatedAt().toInstant()
            );
        }

        Tweet toDomain() {
            return Tweet.of(
                    this.id,
                    null,
                    this.createdAt.atZone(ZoneId.systemDefault()),
                    this.content
            );
        }
    }

    User toDomain() {
        return User.of(
                UUID.fromString(this.id),
                null,
                null,
                this.firstName,
                this.lastName
        );
    }
}
