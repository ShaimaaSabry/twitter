package shaimaa.twitter.application.commands.FollowUser;

import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.contracts.UserRepository;

import java.util.UUID;

@Component
public class FollowUserHandler {
    private final UserRepository userRepository;

    public FollowUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handle(UUID followerUserId, UUID followedUserId) {
        userRepository.follow(followerUserId, followedUserId);
    }
}
