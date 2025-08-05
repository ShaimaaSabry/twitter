package shaimaa.twitter.infrastructure.repositories.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.model.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document(indexName = "users")
class UserElasticDocument {
    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private Set<UUID> following;

    private Set<UUID> followers;

    private List<Tweet> timeline;

    public static UserElasticDocument from(
            User user,
            Set<UUID> following,
            Set<UUID> followers,
            List<Tweet> timeline
            ) {
        return new UserElasticDocument(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                following,
                followers,
                timeline
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
