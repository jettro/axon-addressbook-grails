package nl.gridshore.sample.addressbook.listener

import nl.gridshore.sample.addressbook.Contact
import nl.gridshore.sample.addressbook.event.ContactCreatedEvent
import nl.gridshore.sample.addressbook.event.ContactDeletedEvent
import nl.gridshore.sample.addressbook.event.ContactNameChangedEvent
import org.axonframework.core.Event

/**
 * @author Jettro Coenradie
 */
class ContactEventListener implements org.axonframework.core.eventhandler.EventListener {

    def ContactEventListener(eventBus) {
        eventBus.subscribe this
    }

    void handle(Event event) {
        println "Recevied an event : ${event.class.simpleName}"
        if (event.class in [ContactCreatedEvent, ContactNameChangedEvent, ContactDeletedEvent]) {
            doHandle(event)
        } else {
            println "Event not supported"
        }
    }

    private void doHandle(ContactCreatedEvent event) {
        def contact = new Contact(name: event.name, identifier: event.aggregateIdentifier.toString())
        contact.save()
    }

    private void doHandle(ContactNameChangedEvent event) {
        Contact foundContact = Contact.findByIdentifier(event.aggregateIdentifier.toString())
        foundContact.name = event.newName
        foundContact.save()
    }

    private void doHandle(ContactDeletedEvent event) {
        Contact foundContact = Contact.findByIdentifier(event.aggregateIdentifier.toString())
        foundContact.delete()
    }
}
