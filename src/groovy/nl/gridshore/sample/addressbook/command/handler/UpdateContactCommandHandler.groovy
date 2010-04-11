package nl.gridshore.sample.addressbook.command.handler

import nl.gridshore.sample.addressbook.command.UpdateContactCommand
import nl.gridshore.sample.addressbook.domain.ContactAggregate
import nl.gridshore.sample.addressbook.repository.ContactRepository
import org.axonframework.commandhandling.CommandHandler

/**
 * @author Jettro Coenradie
 */
class UpdateContactCommandHandler implements CommandHandler<UpdateContactCommand> {

    ContactRepository contactRepository

    def UpdateContactCommandHandler(contactRepository, commandBus) {
        this.contactRepository = contactRepository;
        commandBus.subscribe(UpdateContactCommand.class, this)
    }

    Object handle(UpdateContactCommand command) {
        ContactAggregate contact = contactRepository.load(UUID.fromString(command.identifier))
        contact.changeName command.newNameForContact
        contactRepository.save contact
    }
}
