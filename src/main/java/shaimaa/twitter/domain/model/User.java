package shaimaa.twitter.domain.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private UUID id;
    private String firstName;
    private String lastName;

    private User(
            UUID id,
            String firstName,
            String lastName
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static User of(UUID id) {
        return new User(
                id,
               null,
                null
        );
    }

    public static User of(UUID id, String firstName, String lastName) {
        return new User(
                id,
                firstName,
                lastName
        );
    }
}
