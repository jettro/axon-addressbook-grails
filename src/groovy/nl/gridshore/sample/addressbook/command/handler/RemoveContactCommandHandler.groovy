package nl.gridshore.sample.addressbook.command.handler

import nl.gridshore.sample.addressbook.command.RemoveContactCommand
import nl.gridshore.sample.addressbook.domain.ContactAggregate
import nl.gridshore.sample.addressbook.repository.ContactRepository
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.domain.AggregateIdentifierFactory
import org.axonframework.unitofwork.UnitOfWork

/**
 * @author Jettro Coenradie
 */
class RemoveContactCommandHandler implements CommandHandler<RemoveContactCommand> {

    ContactRepository contactRepository

    def RemoveContactCommandHandler(contactRepository, commandBus) {
        this.contactRepository = contactRepository;
        commandBus.subscribe(RemoveContactCommand.class, this)
    }

    Object handle(RemoveContactCommand command, UnitOfWork unitOfWork) {
        ContactAggregate contact = contactRepository.load(AggregateIdentifierFactory.fromString(command.identifier))
        contact.delete()
        contactRepository.save contact
    }
}
