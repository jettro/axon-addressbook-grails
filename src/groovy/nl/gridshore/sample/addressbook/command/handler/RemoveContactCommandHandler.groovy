package nl.gridshore.sample.addressbook.command.handler

import nl.gridshore.sample.addressbook.command.RemoveContactCommand
import nl.gridshore.sample.addressbook.domain.ContactAggregate
import nl.gridshore.sample.addressbook.repository.ContactRepository
import org.axonframework.core.command.CommandHandler

/**
 * @author Jettro Coenradie
 */
class RemoveContactCommandHandler implements CommandHandler<RemoveContactCommand> {

    ContactRepository contactRepository

    def RemoveContactCommandHandler(contactRepository, commandBus) {
        this.contactRepository = contactRepository;
        commandBus.subscribe(RemoveContactCommand.class, this)
    }

    Object handle(RemoveContactCommand command) {
        ContactAggregate contact = contactRepository.load(UUID.fromString(command.identifier))
        contact.delete()
        contactRepository.save contact
    }
}
