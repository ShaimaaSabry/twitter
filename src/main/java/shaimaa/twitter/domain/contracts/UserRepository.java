package shaimaa.twitter.domain.contracts;

import shaimaa.twitter.domain.model.User;

import java.util.Set;
import java.util.UUID;

public interface UserRepository {
    void follow(UUID followerUserId, UUID followedUserId);
    Set<User> getFollowers(UUID userId);
}
