package shaimaa.twitter.presentation.api.v1.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenUserFollow_thenSaveFollow() throws Exception {
        // WHEN
        // THEN
        mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/users/{followed-user-id}/:follow", "followed-user-id")
        ).andExpect(status().isOk());
    }
}
