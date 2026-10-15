package shaimaa.twitter.domain.events;

import shaimaa.twitter.domain.model.Tweet;

public record TweetCreatedEvent(Tweet tweet) implements DomainEvent {
}
