package shaimaa.twitter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import shaimaa.twitter.presentation.api.TweetCreateDTO;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TweetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenTweet_thenCreateTweetTweet() throws Exception {
        // GIVEN
        TweetCreateDTO tweetCreateDTO = new TweetCreateDTO();

        // WHEN
        // THEN
        mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/tweets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tweetCreateDTO))
        ).andExpect(status().isOk());
    }

    @Test
    void givenTweetLike_thenSaveLike() throws Exception {
        // WHEN
        // THEN
        mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/tweets/{tweet-id}/:like", "tweet1")
        ).andExpect(status().isOk());
    }

    @Test
    void givenSearchTweetsTerm_thenReturnTweetList() throws Exception {
        // WHEN
        // THEN
        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/tweets")
                        .param("searchTerm", "twitter")
        ).andExpect(status().isOk());
    }

    @Test
    void givenUserId_thenGenerateTimeline() throws Exception {
        // WHEN
        // THEN
        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/tweets/:timeline")
        ).andExpect(status().isOk());
    }
}
