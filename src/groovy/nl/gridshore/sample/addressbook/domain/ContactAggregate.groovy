package nl.gridshore.sample.addressbook.domain

import nl.gridshore.sample.addressbook.event.AddressAddedEvent
import nl.gridshore.sample.addressbook.event.ContactCreatedEvent
import nl.gridshore.sample.addressbook.event.ContactDeletedEvent
import nl.gridshore.sample.addressbook.event.ContactNameChangedEvent
import org.axonframework.core.AbstractEventSourcedAggregateRoot
import org.axonframework.core.AggregateDeletedEvent
import org.axonframework.core.DomainEvent

class ContactAggregate extends AbstractEventSourcedAggregateRoot {

    private def addresses = []

    ContactAggregate(String name) {
        apply(new ContactCreatedEvent(name))
    }

    ContactAggregate(UUID identifier) {
        super(identifier)
    }

    void changeName(String name) {
        apply(new ContactNameChangedEvent(name))
    }

    void delete() {
        apply(new ContactDeletedEvent())
    }


    void registerAddress(Address address) {
        // TODO add validation
        apply(new AddressAddedEvent(address))
    }

    protected AggregateDeletedEvent createDeletedEvent() {
        return new ContactDeletedEvent()
    }

    protected void handle(DomainEvent event) {
        println "Contact aggregate recevied an event : ${event.class.simpleName}"
        if (event.class in [AddressAddedEvent]) {
            doHandle(event)
        } else {
            println "Event not supported"
        }
    }

    private void doHandle(AddressAddedEvent event) {
        println "Adding an address to the contact"
        addresses.add(event.addedAddress)
    }

}
