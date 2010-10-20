package nl.gridshore.sample.addressbook.repository

import nl.gridshore.sample.addressbook.domain.ContactAggregate
import org.axonframework.domain.DomainEvent
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.domain.AggregateIdentifier

/**
 * @author Jettro Coenradie
 */
class ContactRepository extends EventSourcingRepository<ContactAggregate> {

    public void eventBus(EventBus bus) {
        super.setEventBus bus
    }

    public String getTypeIdentifier() {
        return "nl.gridshore.sample.addressbook.ContactAggregate";
    }

    @Override protected ContactAggregate instantiateAggregate(AggregateIdentifier aggregateIdentifier, DomainEvent domainEvent) {
        return new ContactAggregate(aggregateIdentifier)
    }
}
