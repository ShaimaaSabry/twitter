package shaimaa.twitter.infrastructure.repositories.sql.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
interface UserJpaRepository extends CrudRepository<UserEntity, UUID> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO user_follow (follower_id, followed_id)
        VALUES (:followerId, :followedId)
        """, nativeQuery = true)
    void followUser(UUID followerId, UUID followedId);

    @Query(value = """
        SELECT u.* 
        FROM users u
        JOIN user_follow uf ON uf.follower_id = u.id
        WHERE uf.followed_id = :userId
        """, nativeQuery = true)
    List<UserEntity> findFollowersByUserId(UUID userId);
}
