package nl.gridshore.sample.addressbook.event

import org.axonframework.domain.DomainEvent

/**
 * @author Jettro Coenradie
 */
class ContactCreatedEvent extends DomainEvent {
    final String name

    ContactCreatedEvent(name) {
        this.name = name;
    }

}
