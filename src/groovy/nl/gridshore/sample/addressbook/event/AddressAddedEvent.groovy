package nl.gridshore.sample.addressbook.event

import nl.gridshore.sample.addressbook.domain.Address
import org.axonframework.core.DomainEvent

/**
 * @author Jettro Coenradie
 */
class AddressAddedEvent extends DomainEvent {
    final Address addedAddress;

    def AddressAddedEvent(addedAddress) {
        this.addedAddress = addedAddress
    }
}
