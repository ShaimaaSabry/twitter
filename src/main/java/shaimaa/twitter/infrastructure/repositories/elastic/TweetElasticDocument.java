package shaimaa.twitter.infrastructure.repositories.elastic;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.model.User;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document(indexName = "tweets")
public class TweetElasticDocument {
    @Id
    private UUID id;

    @Field(type = FieldType.Object)
    private UserDto user;

    private String content;

    @Field(name = "created_at", type = FieldType.Date, format = DateFormat.date_time)
    private ZonedDateTime createdAt;

    @Data
    @AllArgsConstructor
    static class UserDto {
        @Field(type = FieldType.Keyword)
        private UUID id;
        private String firstName;
        private String lastName;

        public static UserDto from(User user) {
            return new UserDto(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName()
            );
        }

        public static UserDto from(UserElasticDocument user) {
            return new UserDto(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName()
            );
        }

        User toDomain() {
            return User.of(
                    this.id,
                    null,
                    null,
                    firstName,
                    lastName
            );
        }
    }

    static TweetElasticDocument from(Tweet tweet, UserElasticDocument userElasticDocument) {
        return new TweetElasticDocument(
                tweet.getId(),
                UserDto.from(userElasticDocument),
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
