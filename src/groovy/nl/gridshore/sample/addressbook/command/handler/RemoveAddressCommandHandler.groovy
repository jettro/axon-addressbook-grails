package nl.gridshore.sample.addressbook.command.handler

import nl.gridshore.sample.addressbook.command.RemoveAddressCommand
import nl.gridshore.sample.addressbook.domain.ContactAggregate
import nl.gridshore.sample.addressbook.repository.ContactRepository
import org.axonframework.commandhandling.CommandHandler

/**
 * @author Jettro Coenradie
 */
class RemoveAddressCommandHandler implements CommandHandler<RemoveAddressCommand> {
    ContactRepository contactRepository

    def RemoveAddressCommandHandler(contactRepository, commandBus) {
        this.contactRepository = contactRepository;
        commandBus.subscribe(RemoveAddressCommand.class, this)
    }

    Object handle(RemoveAddressCommand command) {
        ContactAggregate contact = contactRepository.load(UUID.fromString(command.identifier))
        contact.removeAddress(command.addressType)
        contactRepository.save contact
    }

}
