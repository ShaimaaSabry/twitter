package shaimaa.twitter.domain.contracts;

import shaimaa.twitter.domain.events.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
