package shaimaa.twitter.presentation.api.v1.tweet;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import shaimaa.twitter.application.commands.CreateTweet.CreateTweetHandler;
import shaimaa.twitter.application.queries.GetTimeline.GetTimelineHandler;
import shaimaa.twitter.application.queries.GetUserTweets.GetUserTweetsHandler;
import shaimaa.twitter.application.queries.SearchTweets.SearchTweetsHandler;
import shaimaa.twitter.domain.model.Tweet;
import shaimaa.twitter.domain.model.User;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TweetController.class)
class TweetControllerTest {
    private static final String BASE_URL = "/v1/tweets";
    private static final String USER_HEADER = "X-User-Id";

    @TestConfiguration
    static class MockBeans {
        @Bean
        public CreateTweetHandler createTweetHandler() {
            return Mockito.mock(CreateTweetHandler.class);
        }

        @Bean
        public GetUserTweetsHandler getUserTweetsHandler() {
            return Mockito.mock(GetUserTweetsHandler.class);
        }

        @Bean
        public SearchTweetsHandler searchTweetsHandler() {
            return Mockito.mock(SearchTweetsHandler.class);
        }

        @Bean
        public GetTimelineHandler getTimelineHandler() {
            return Mockito.mock(GetTimelineHandler.class);
        }
    }

    @Autowired
    private GetUserTweetsHandler getUserTweetsHandler;

    @Autowired
    private SearchTweetsHandler searchTweetsHandler;

    @Autowired
    private GetTimelineHandler getTimelineHandler;

    @Autowired
    private MockMvc mockMvc;

    UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    User user = User.of(
            userId,
            "jdoe",
            "https://avatar.example/jdoe.png",
            "John",
            "Doe"
    );
    Tweet tweet = Tweet.of(
            UUID.randomUUID(),
            user,
            ZonedDateTime.now(),
            "Hello world"
    );

    @Test
    void givenUserId_thenReturnProfileTimeline() throws Exception {
        // GIVEN
        Mockito.when(getUserTweetsHandler.handle(userId)).thenReturn(List.of(tweet));

        // WHEN // THEN
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(BASE_URL + "/profile")
                        .header(USER_HEADER, userId)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(tweet.getId().toString()))
                .andExpect(jsonPath("$[0].author.id").value(user.getId().toString()))
                .andExpect(jsonPath("$[0].author.handle").value(user.getHandle()))
                .andExpect(jsonPath("$[0].author.avatarUrl").value(user.getAvatarUrl()))
                .andExpect(jsonPath("$[0].author.name").value("%s %s".formatted(user.getFirstName(), user.getLastName())))
                .andExpect(jsonPath("$[0].content").value(tweet.getContent()))
                .andExpect(jsonPath("$[0].createdAt").exists());
    }

    @Test
    void givenSearchTerm_thenReturnTweetList() throws Exception {
        // WHEN
        // THEN
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(BASE_URL + "/search")
                        .param("searchTerm", "twitter")
        ).andExpect(status().isOk());
    }

    @Test
    void givenUserId_thenReturnTimeline() throws Exception {
        // GIVEN
        Mockito.when(getTimelineHandler.handle(userId)).thenReturn(List.of(tweet));

        // WHEN
        // THEN
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(BASE_URL + "/timeline")
                        .header(USER_HEADER, userId)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(tweet.getId().toString()))
                .andExpect(jsonPath("$[0].author.id").value(user.getId().toString()))
                .andExpect(jsonPath("$[0].author.handle").value(user.getHandle()))
                .andExpect(jsonPath("$[0].author.avatarUrl").value(user.getAvatarUrl()))
                .andExpect(jsonPath("$[0].author.name").value("%s %s".formatted(user.getFirstName(), user.getLastName())))
                .andExpect(jsonPath("$[0].content").value(tweet.getContent()))
                .andExpect(jsonPath("$[0].createdAt").exists());
    }
}
