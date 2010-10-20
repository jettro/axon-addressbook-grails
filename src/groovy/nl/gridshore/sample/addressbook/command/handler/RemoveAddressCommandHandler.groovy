package nl.gridshore.sample.addressbook.command.handler

import nl.gridshore.sample.addressbook.command.RemoveAddressCommand
import nl.gridshore.sample.addressbook.domain.ContactAggregate
import nl.gridshore.sample.addressbook.repository.ContactRepository
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.domain.AggregateIdentifierFactory
import org.axonframework.unitofwork.UnitOfWork

/**
 * @author Jettro Coenradie
 */
class RemoveAddressCommandHandler implements CommandHandler<RemoveAddressCommand> {
    ContactRepository contactRepository

    def RemoveAddressCommandHandler(contactRepository, commandBus) {
        this.contactRepository = contactRepository;
        commandBus.subscribe(RemoveAddressCommand.class, this)
    }

    Object handle(RemoveAddressCommand command, UnitOfWork unitOfWork) {
        ContactAggregate contact = contactRepository.load(AggregateIdentifierFactory.fromString(command.identifier))
        contact.removeAddress(command.addressType)
        contactRepository.save contact
    }
}
