package shaimaa.twitter.presentation.api.v1.user;

//import org.springframework.security.core.Authentication;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaimaa.twitter.application.commands.FollowUser.FollowUserHandler;

import java.util.UUID;

@RestController
@RequestMapping("v1/users")
@Tag(name = "Users", description = "APIs for following users.")
class UserController {
    private final FollowUserHandler followUserHandler;

    UserController(FollowUserHandler followUserHandler) {
        this.followUserHandler = followUserHandler;
    }

    @PostMapping("{followedUserId}/:follow")
    void follow(
//            Authentication authentication,
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID followedUserId
    ) {
//        UUID userId = UUID.fromString(authentication.getName());
        followUserHandler.handle(userId, followedUserId);
    }
}
