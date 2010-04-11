package nl.gridshore.sample.addressbook.event

import org.axonframework.domain.DomainEvent

/**
 * @author Jettro Coenradie
 */
class ContactNameChangedEvent extends DomainEvent {
    final String newName


    ContactNameChangedEvent(newName) {
        this.newName = newName;
    }
}
