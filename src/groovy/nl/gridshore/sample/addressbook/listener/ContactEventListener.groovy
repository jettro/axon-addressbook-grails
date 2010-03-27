package nl.gridshore.sample.addressbook.listener

import nl.gridshore.sample.addressbook.AddressEntry
import nl.gridshore.sample.addressbook.ContactEntry
import nl.gridshore.sample.addressbook.domain.Address
import nl.gridshore.sample.addressbook.event.AddressAddedEvent
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
        println "Contact event listener recevied an event : ${event.class.simpleName}"
        if (event.class in [ContactCreatedEvent, ContactNameChangedEvent, ContactDeletedEvent, AddressAddedEvent]) {
            doHandle(event)
        } else {
            println "Event not supported"
        }
    }

    private void doHandle(ContactCreatedEvent event) {
        def contact = new ContactEntry(name: event.name, identifier: event.aggregateIdentifier.toString())
        contact.save()
    }

    private void doHandle(ContactNameChangedEvent event) {
        ContactEntry foundContact = ContactEntry.findByIdentifier(event.aggregateIdentifier.toString())
        foundContact.name = event.newName
        foundContact.save()
    }

    private void doHandle(ContactDeletedEvent event) {
        ContactEntry foundContact = ContactEntry.findByIdentifier(event.aggregateIdentifier.toString())
        foundContact.delete()
    }

    private void doHandle(AddressAddedEvent event) {
        ContactEntry foundContact = ContactEntry.findByIdentifier(event.aggregateIdentifier.toString())
        Address address = event.addedAddress
        AddressEntry addressEntry = new AddressEntry(
                contactName: foundContact.name,
                contactIdentifier: foundContact.identifier,
                streetAndNumber: address.streetAndNumber,
                zipCode: address.zipCode,
                city: address.city,
                addressType: event.addressType)
        addressEntry.save()
    }
}
