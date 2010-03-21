package nl.gridshore.sample.addressbook.domain

import nl.gridshore.sample.addressbook.event.ContactCreatedEvent
import nl.gridshore.sample.addressbook.event.ContactDeletedEvent
import nl.gridshore.sample.addressbook.event.ContactNameChangedEvent
import org.axonframework.core.AbstractEventSourcedAggregateRoot
import org.axonframework.core.AggregateDeletedEvent
import org.axonframework.core.DomainEvent

class ContactAggregate extends AbstractEventSourcedAggregateRoot {

  ContactAggregate(String name) {
    apply(new ContactCreatedEvent(name))
  }

  ContactAggregate(UUID identifier) {
    super(identifier)
  }

  void changeName(String name) {
    apply(new ContactNameChangedEvent(name))
  }

  static constraints = {
  }

  protected AggregateDeletedEvent createDeletedEvent() {
    return new ContactDeletedEvent()
  }

  protected void handle(DomainEvent domainEvent) {
    // TODO try to use reflection and the first argument of the methods starting with handle
  }
}
