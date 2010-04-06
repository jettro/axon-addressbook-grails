package nl.gridshore.sample.addressbook.contact

import nl.gridshore.sample.addressbook.command.CreateContactCommand
import nl.gridshore.sample.addressbook.command.RegisterAddressCommand
import nl.gridshore.sample.addressbook.command.RemoveContactCommand
import nl.gridshore.sample.addressbook.command.UpdateContactCommand
import nl.gridshore.sample.addressbook.domain.AddressType
import org.axonframework.core.command.CommandBus

class ContactCommandHandlerService {

    boolean transactional = true

    CommandBus commandBus

    def createContact(String contactName) {
        commandBus.dispatch(new CreateContactCommand(newContactName: contactName))
    }

    def updateContactName(String identifier, String newContactName) {
        commandBus.dispatch(new UpdateContactCommand(identifier: identifier, newNameForContact: newContactName))
    }

    def removeContact(String identifier) {
        commandBus.dispatch(new RemoveContactCommand(identifier: identifier))
    }

    def registerAddress(AddressType addressType, String identifier, String streetNumber, String zipCode, String city) {
        commandBus.dispatch(
                new RegisterAddressCommand(
                        addressType: addressType,
                        identifier: identifier,
                        streetNumber: streetNumber,
                        zipCode: zipCode,
                        city: city)
        )
    }
}
