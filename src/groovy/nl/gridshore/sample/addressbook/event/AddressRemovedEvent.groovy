package nl.gridshore.sample.addressbook.event

import nl.gridshore.sample.addressbook.domain.AddressType
import org.axonframework.domain.DomainEvent

/**
 * @author Jettro Coenradie
 */
class AddressRemovedEvent extends DomainEvent {
    final AddressType addressType

    def AddressRemovedEvent(addressType) {
        this.addressType = addressType
    }

}
