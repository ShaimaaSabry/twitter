package shaimaa.twitter.infrastructure.repositories.mongodb;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shaimaa.twitter.domain.model.User;
import shaimaa.twitter.domain.contracts.UserRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Component
class UserMongoRepositoryImpl implements UserRepository {
    private final UserMongoRepository userMongoRe;

    UserMongoRepositoryImpl(
            @Lazy final UserMongoRepository userMongoRe
    ) {
        this.userMongoRe = userMongoRe;
    }

    @Override
    @Transactional
    public void follow(UUID followerUserId, UUID followedUserId) {
        UserMongoDocument follower = userMongoRe.findById(followerUserId.toString())
                .orElseThrow(() -> new IllegalArgumentException("Follower user not found: " + followerUserId));

        follower.getFollowing().add(followedUserId);
        userMongoRe.save(follower);

        UserMongoDocument followed = userMongoRe.findById(followedUserId.toString())
                .orElseThrow(() -> new IllegalArgumentException("Followed user not found: " + followedUserId));
        followed.getFollowers().add(followerUserId);
        userMongoRe.save(followed);
    }

    @Override
    public Set<User> getFollowers(UUID userId) {
        UserMongoDocument userMongoDocument = userMongoRe.findById(userId.toString())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        return userMongoDocument
                .getFollowers()
                .stream()
                .map(User::of)
                .collect(Collectors.toSet());
    }
}
