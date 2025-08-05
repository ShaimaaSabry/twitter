package shaimaa.twitter.infrastructure.repositories.elastic;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.contracts.UserRepository;
import shaimaa.twitter.domain.model.User;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

//@Primary
@Component
public class UserElasticRepositoryImpl implements UserRepository {
    private final UserElasticRepository userElasticRepository;

    public UserElasticRepositoryImpl(@Lazy UserElasticRepository userElasticRepository) {
        this.userElasticRepository = userElasticRepository;
    }

    @Override
    public void follow(UUID followerUserId, UUID followedUserId) {
        UserElasticDocument follower = userElasticRepository.findById(followerUserId)
                .orElseThrow(() -> new IllegalArgumentException("Follower user not found: " + followerUserId));

        if (follower.getFollowing() == null) {
            follower.setFollowing(new java.util.HashSet<>());
        }
        follower.getFollowing().add(followedUserId);

        userElasticRepository.save(follower);

        UserElasticDocument followed = userElasticRepository.findById(followedUserId)
                .orElseThrow(() -> new IllegalArgumentException("Followed user not found: " + followedUserId));

        if (followed.getFollowers() == null) {
            followed.setFollowers(new java.util.HashSet<>());
        }
        followed.getFollowers().add(followerUserId);

        userElasticRepository.save(followed);
    }

    @Override
    public Set<User> getFollowers(UUID userId) {
        UserElasticDocument userElasticDocument = userElasticRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        return userElasticDocument.getFollowers()
                .stream()
                .map(User::of)
                .collect(Collectors.toSet());
    }
}
