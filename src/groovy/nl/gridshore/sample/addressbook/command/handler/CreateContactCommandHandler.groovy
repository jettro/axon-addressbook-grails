package nl.gridshore.sample.addressbook.command.handler

import nl.gridshore.sample.addressbook.command.CreateContactCommand
import nl.gridshore.sample.addressbook.domain.ContactAggregate
import nl.gridshore.sample.addressbook.repository.ContactRepository
import org.axonframework.core.command.CommandHandler

/**
 * @author Jettro Coenradie
 */
class CreateContactCommandHandler implements CommandHandler<CreateContactCommand> {

    ContactRepository contactRepository

    def CreateContactCommandHandler(contactRepository, commandBus) {
        this.contactRepository = contactRepository;
        commandBus.subscribe(CreateContactCommand.class, this)
    }

    Object handle(CreateContactCommand command) {
        println "Handle new contact command for ${command.newContactName}"
        ContactAggregate contact = new ContactAggregate(command.newContactName)
        contactRepository.save contact
        return contact.identifier.toString()
    }
}
