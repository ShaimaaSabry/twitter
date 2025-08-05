package shaimaa.twitter.infrastructure.repositories.sql.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import shaimaa.twitter.domain.model.User;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;

    private String handle;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        return userEntity;
    }

    public User toDomain() {
        return User.of(
                this.id,
                handle,
                avatarUrl,
                firstName,
                lastName
        );
    }
}
