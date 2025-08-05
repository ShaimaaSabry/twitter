package shaimaa.twitter.infrastructure.repositories.sql.tweet;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.infrastructure.repositories.sql.user.UserEntity;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tweets")
public class TweetEntity {
    @Id
    private UUID id;

    @ManyToOne
    private UserEntity user;

    private String content;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    static TweetEntity from(Tweet tweet) {
        return new TweetEntity(
                tweet.getId(),
                UserEntity.from(tweet.getUser()),
                tweet.getContent(),
                tweet.getCreatedAt()
        );
    }

    Tweet toDomain() {
        return Tweet.of(
                id,
                user.toDomain(),
                createdAt,
                content
        );
    }
}
