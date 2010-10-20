package nl.gridshore.sample.addressbook.event

import org.axonframework.domain.AggregateDeletedEvent
import org.axonframework.domain.AggregateIdentifier
import org.axonframework.domain.DomainEvent

/**
 * @author Jettro Coenradie
 */
class ContactDeletedEvent extends DomainEvent implements AggregateDeletedEvent {
    AggregateIdentifier contactIdentifier() {
        return getAggregateIdentifier()
    }
}
