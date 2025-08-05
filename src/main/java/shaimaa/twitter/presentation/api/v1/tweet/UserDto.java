package shaimaa.twitter.presentation.api.v1.tweet;

import shaimaa.twitter.domain.model.User;

import java.util.UUID;

public record UserDto(
    UUID id,
    String firstName,
    String lastName
) {
    public static UserDto from(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName());
    }
}
