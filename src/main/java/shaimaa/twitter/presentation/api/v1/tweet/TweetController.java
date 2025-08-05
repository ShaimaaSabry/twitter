package shaimaa.twitter.presentation.api.v1.tweet;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestHeader;
import shaimaa.twitter.application.commands.CreateTweet.CreateTweetHandler;
import shaimaa.twitter.application.queries.GetTimeline.GetTimelineHandler;
import shaimaa.twitter.application.queries.GetUserTweets.GetUserTweetsHandler;
import shaimaa.twitter.application.queries.SearchTweets.SearchTweetsHandler;
import shaimaa.twitter.domain.model.Tweet;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/tweets")
@Tag(name = "Tweets", description = "APIs for creating and searching tweets, and retrieving timelines.")
class TweetController {
    private final CreateTweetHandler createTweetHandler;
    private final GetUserTweetsHandler getUserTweetsHandler;
    private final SearchTweetsHandler searchTweetsHandler;
    private final GetTimelineHandler getTimelineHandler;

    TweetController(
            final CreateTweetHandler createTweetHandler,
            final GetUserTweetsHandler getUserTweetsHandler,
            final SearchTweetsHandler searchTweetsHandler,
            final GetTimelineHandler getTimelineHandler
    ) {
        this.createTweetHandler = createTweetHandler;
        this.getUserTweetsHandler = getUserTweetsHandler;
        this.searchTweetsHandler = searchTweetsHandler;
        this.getTimelineHandler = getTimelineHandler;
    }

    @PostMapping
    TweetDTO createTweet(
//            Authentication authentication,
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody CreateTweetRequest request
    ) {
//        UUID userId = UUID.fromString(authentication.getName());
        Tweet tweet = this.createTweetHandler.handle(request.toCommand(userId));
        return TweetDTO.from(tweet);
    }

    @GetMapping("profile")
    List<TweetDTO> getProfileTimeline(@RequestHeader("X-User-Id") UUID userId) {
        List<Tweet> tweets = this.getUserTweetsHandler.handle(userId);

        return tweets
                .stream()
                .map(TweetDTO::from)
                .toList();
    }

//    @PostMapping("{tweetId}/:like")
//    void like(
////            Authentication authentication,
//            String tweetId
//    ) {
////        UUID userId = UUID.fromString(authentication.getName());
//    }

    @GetMapping("search")
    TweetDTO[] searchTweets(String searchTerm) {
        List<Tweet> tweets = this.searchTweetsHandler.handle(searchTerm);

        return tweets
                .stream()
                .map(TweetDTO::from)
                .toArray(TweetDTO[]::new);
    }

    @GetMapping("timeline")
    List<TweetDTO> getTimeline(
//            Authentication authentication
            @RequestHeader("X-User-Id") UUID userId
    ) {
//        UUID userId = UUID.fromString(authentication.getName());

        List<Tweet> tweets = this.getTimelineHandler.handle(userId);

        return tweets
                .stream()
                .map(TweetDTO::from)
                .toList();
    }
}
