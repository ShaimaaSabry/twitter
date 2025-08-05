package shaimaa.twitter.domain.contracts;

import shaimaa.twitter.domain.model.Tweet;

import java.util.List;
import java.util.UUID;

public interface TweetRepository {
    Tweet create(Tweet tweet);
    List<Tweet> getTweetsByUserId(UUID userId, int limit);
    List<Tweet> search(String searchTerm, int limit);

    List<Tweet> generateTimeline(UUID userId, int limit);
    List<Tweet> getTimeline(UUID userId);
    void saveTimeline(UUID userId, List<Tweet> tweets);
}
