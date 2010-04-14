package nl.gridshore.sample.addressbook.event

import nl.gridshore.sample.addressbook.domain.AddressType
import nl.gridshore.sample.addressbook.domain.Address
import org.axonframework.domain.DomainEvent

/**
 * @author Jettro Coenradie
 */
class AddressChangedEvent extends DomainEvent{
    final Address addedAddress;
    final AddressType addressType

    def AddressChangedEvent(addressType, addedAddress) {
        this.addedAddress = addedAddress
        this.addressType = addressType
    }

}
