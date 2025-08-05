package shaimaa.twitter.presentation.api.v1.tweet;

import shaimaa.twitter.domain.model.User;

import java.util.UUID;

public record UserDto(
    UUID id,
    String handle,
    String avatarUrl,
    String name
) {
    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getHandle(),
                user.getAvatarUrl(),
                "%s %s".formatted(
                user.getFirstName(),
                user.getLastName()
                )
        );
    }
}
