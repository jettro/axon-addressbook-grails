import nl.gridshore.sample.addressbook.repository.ContactRepository
import nl.gridshore.sample.addressbook.listener.ContactEventListener
import nl.gridshore.sample.addressbook.command.handler.CreateContactCommandHandler
import nl.gridshore.sample.addressbook.command.handler.UpdateContactCommandHandler
import nl.gridshore.sample.addressbook.command.handler.RemoveContactCommandHandler
import nl.gridshore.sample.addressbook.command.handler.RegisterAddressCommandHandler
import org.axonframework.commandhandling.SimpleCommandBus
import org.axonframework.eventhandling.SimpleEventBus
import org.axonframework.eventstore.fs.FileSystemEventStore
import org.axonframework.eventstore.XStreamEventSerializer

// Place your Spring DSL code here
beans = {
    contactRepository(ContactRepository) {
        eventBus = ref("eventBus")
        eventStore = ref("eventStore")
    }

    contactEventListener(ContactEventListener, ref("eventBus"))

    createContactCommandHandler(CreateContactCommandHandler, ref("contactRepository"), ref("commandBus"))
    updateContactCommandHandler(UpdateContactCommandHandler, ref("contactRepository"), ref("commandBus"))
    removeContactCommandHandler(RemoveContactCommandHandler, ref("contactRepository"), ref("commandBus"))
    registerAddressCommandHandler(RegisterAddressCommandHandler, ref("contactRepository"), ref("commandBus"))

    // axon beans
    commandBus(SimpleCommandBus)

    eventBus(SimpleEventBus)

    eventStore(FileSystemEventStore, ref("xstreamSerializer")) {
        baseDir = "file:/Users/jcoenradie/temp/axonrepo"
    }

    xstreamSerializer(XStreamEventSerializer)
}