package shaimaa.twitter.infrastructure.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import shaimaa.twitter.domain.contracts.DomainEventPublisher;
import shaimaa.twitter.domain.events.DomainEvent;

@Component
class SpringDomainEventPublisher implements DomainEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    SpringDomainEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
