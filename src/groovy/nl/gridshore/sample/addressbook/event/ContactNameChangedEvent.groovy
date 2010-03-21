package nl.gridshore.sample.addressbook.event

import org.axonframework.core.DomainEvent

/**
 * @author Jettro Coenradie
 */
class ContactNameChangedEvent extends DomainEvent {
  final String newName


  ContactNameChangedEvent(newName) {
    this.newName = newName;
  }
}
