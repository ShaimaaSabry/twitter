package shaimaa.twitter.application.queries.SearchTweets;

import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.contracts.TweetRepository;
import shaimaa.twitter.domain.model.Tweet;

import java.util.List;

@Component
public class SearchTweetsHandler {
    private static final int LIMIT = 10; // Default limit for search results

    private final TweetRepository tweetRepository;

    SearchTweetsHandler(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public List<Tweet> handle(String searchTerm) {
        return this.tweetRepository.search(searchTerm, LIMIT);
    }
}
