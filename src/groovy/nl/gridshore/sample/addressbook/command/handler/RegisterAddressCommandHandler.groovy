package nl.gridshore.sample.addressbook.command.handler

import nl.gridshore.sample.addressbook.command.RegisterAddressCommand
import nl.gridshore.sample.addressbook.domain.Address
import nl.gridshore.sample.addressbook.domain.ContactAggregate
import nl.gridshore.sample.addressbook.repository.ContactRepository
import org.axonframework.core.command.CommandHandler

/**
 * @author Jettro Coenradie
 */
class RegisterAddressCommandHandler implements CommandHandler<RegisterAddressCommand> {
    ContactRepository contactRepository

    def RegisterAddressCommandHandler(contactRepository, commandBus) {
        this.contactRepository = contactRepository;
        commandBus.subscribe(RegisterAddressCommand.class, this)
    }

    Object handle(RegisterAddressCommand command) {
        ContactAggregate contact = contactRepository.load(UUID.fromString(command.identifier))
        contact.registerAddress(command.addressType, new Address(command.city, command.streetNumber, command.zipCode))
        contactRepository.save contact
    }
}
