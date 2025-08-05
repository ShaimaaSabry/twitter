//package shaimaa.twitter.domain;
//
//import shaimaa.twitter.domain.model.Tweet;
//import shaimaa.twitter.infrastructure.repositories.mongodb.TweetMongoRepository;
//import shaimaa.twitter.infrastructure.repositories.mongodb.TweetMongoDocument;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//class TweetServiceTest {
//    @Autowired
//    TweetMongoRepository mongodbTweetRepository;
//
//    @Autowired
//    UserFollowMongoRepository mongodbUserFollowRepository;
//
//    @Autowired
//    private TweetService tweetService;
//
//    @Test
//    void givenTweet_thenCreateTweet() {
//        // WHEN
//        Tweet tweet = new Tweet("user1", "This is a tweet.");
//        tweet = tweetService.create(tweet);
//
//        // THEN
//        Optional<TweetMongoDocument> savedTweetEntity = mongodbTweetRepository.findById(tweet.getId());
//        Assertions.assertTrue(savedTweetEntity.isPresent());
//        Assertions.assertEquals(tweet.getContent(), savedTweetEntity.get().getContent());
//    }
//
////    @Test
//    void givenTweetLike_thenSaveLike() {
//        // WHEN
//        tweetService.like("user1", "tweet1");
//
//        // THEN
//    }
//
//    @Test
//    void givenSearchTerm_thenReturnTweetList() {
//        // GIVEN
//        TweetMongoDocument tweetMongoDocument1 = new TweetMongoDocument(
//                "tweet1",
//                "user1",
//                "timestamp",
//                "This a tweet containing twitter.");
//        mongodbTweetRepository.save(tweetMongoDocument1);
//
//        TweetMongoDocument tweetMongoDocument2 = new TweetMongoDocument(
//                "tweet2",
//                "user2",
//                "timestamp",
//                "Don't return this tweet.");
//        mongodbTweetRepository.save(tweetMongoDocument2);
//
//        // WHEN
//        List<Tweet> tweets =  tweetService.search("twitter");
//
//        // THEN
//        Assertions.assertEquals(1, tweets.size());
//        Assertions.assertEquals("tweet1", tweets.get(0).getId());
//    }
//
//    @Test
//    void givenUserId_thenGenerateTimeline() {
//        // GIVEN
//        UserFollowEntity userFollowEntity1 = new UserFollowEntity("user1", "user2");
//        mongodbUserFollowRepository.save(userFollowEntity1);
//        TweetMongoDocument tweetMongoDocument1 = new TweetMongoDocument(
//                "tweet1",
//                "user2",
//                "timestamp",
//                "tweet1 content.");
//        mongodbTweetRepository.save(tweetMongoDocument1);
//
//        UserFollowEntity userFollowEntity2 = new UserFollowEntity("user1", "user3");
//        mongodbUserFollowRepository.save(userFollowEntity2);
//        TweetMongoDocument tweetMongoDocument2 = new TweetMongoDocument(
//                "tweet2",
//                "user3",
//                "timestamp",
//                "tweet2 content.");
//        mongodbTweetRepository.save(tweetMongoDocument2);
//
//        // WHEN
//        List<Tweet> tweets = tweetService.generateTimeline("user1");
//
//        // THEN
//        Assertions.assertEquals(2, tweets.size());
//    }
//}
