package shaimaa.twitter.infrastructure.repositories.sql.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.contracts.UserRepository;
import shaimaa.twitter.domain.model.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
class UserSqlRepository implements UserRepository {
    private final Logger logger = LoggerFactory.getLogger(UserSqlRepository.class);

    private final UserJpaRepository userJpaRepository;

    UserSqlRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public void follow(UUID followerUserId, UUID followedUserId) {
        logger.debug(
                "User {} is following user {}",
                followerUserId,
                followedUserId
        );
        this.userJpaRepository.followUser(followerUserId, followedUserId);
    }

    @Override
    public Set<User> getFollowers(UUID userId) {
        List<UserEntity> followersUserEntities = userJpaRepository.findFollowersByUserId(userId);

        return followersUserEntities
                .stream()
                .map(UserEntity::toDomain)
                .collect(Collectors.toSet());
    }
}
