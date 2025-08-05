package shaimaa.twitter.infrastructure.repositories.mongodb;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoRepositoriesConfig {
    // No beans needed; this just turns on Mongo repos when not in 'test'
}
