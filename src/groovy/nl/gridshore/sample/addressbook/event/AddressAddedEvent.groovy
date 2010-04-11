package nl.gridshore.sample.addressbook.event

import nl.gridshore.sample.addressbook.domain.Address
import nl.gridshore.sample.addressbook.domain.AddressType
import org.axonframework.domain.DomainEvent

/**
 * @author Jettro Coenradie
 */
class AddressAddedEvent extends DomainEvent {
    final Address addedAddress;
    final AddressType addressType

    def AddressAddedEvent(addressType, addedAddress) {
        this.addedAddress = addedAddress
        this.addressType = addressType
    }
}
