package shaimaa.twitter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Twitter APIs",
                version = "1.0.0",
                description = "Users can create tweets, search tweets, follow other users, and view their timeline."
        )
)
//@EnableMongoRepositories
public class TwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterApplication.class);
    }
}
