package nl.gridshore.sample.addressbook.repository

import nl.gridshore.sample.addressbook.domain.ContactAggregate
import org.axonframework.domain.DomainEvent
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventsourcing.EventSourcingRepository

/**
 * @author Jettro Coenradie
 */
class ContactRepository extends EventSourcingRepository<ContactAggregate> {

    public void eventBus(EventBus bus) {
        super.setEventBus bus
    }

    protected String getTypeIdentifier() {
        return "nl.gridshore.sample.addressbook.ContactAggregate";
    }

    protected ContactAggregate instantiateAggregate(UUID uuid, DomainEvent domainEvent) {
        return new ContactAggregate(uuid);
    }
}
