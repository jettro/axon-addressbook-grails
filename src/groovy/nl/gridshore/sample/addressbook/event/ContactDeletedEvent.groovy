package nl.gridshore.sample.addressbook.event

import org.axonframework.core.AggregateDeletedEvent

/**
 * @author Jettro Coenradie
 */
class ContactDeletedEvent extends AggregateDeletedEvent {
  UUID contactIdentifier() {
    return getAggregateIdentifier();
  }
}
