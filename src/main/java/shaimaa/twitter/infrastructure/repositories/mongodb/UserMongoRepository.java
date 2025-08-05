package shaimaa.twitter.infrastructure.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface UserMongoRepository extends MongoRepository<UserMongoDocument, String> {
}
