package nl.gridshore.sample.addressbook.listener

import nl.gridshore.sample.addressbook.AddressEntry
import nl.gridshore.sample.addressbook.ContactEntry
import nl.gridshore.sample.addressbook.domain.Address
import nl.gridshore.sample.addressbook.event.AddressAddedEvent
import nl.gridshore.sample.addressbook.event.ContactCreatedEvent
import nl.gridshore.sample.addressbook.event.ContactDeletedEvent
import nl.gridshore.sample.addressbook.event.ContactNameChangedEvent
import org.axonframework.domain.Event
import nl.gridshore.sample.addressbook.event.AddressRemovedEvent
import nl.gridshore.sample.addressbook.event.AddressChangedEvent

/**
 * @author Jettro Coenradie
 */
class ContactEventListener implements org.axonframework.eventhandling.EventListener {

    def ContactEventListener(eventBus) {
        eventBus.subscribe this
    }

    void handle(Event event) {
        println "Contact event listener recevied an event : ${event.class.simpleName}"
        if (event.class in [ContactCreatedEvent, ContactNameChangedEvent, ContactDeletedEvent,
                AddressAddedEvent, AddressRemovedEvent, AddressChangedEvent]) {
            doHandle(event)
        } else {
            println "ContactEventListener : Event not supported"
        }
    }

    private void doHandle(ContactCreatedEvent event) {
        def contact = new ContactEntry(name: event.name, identifier: event.aggregateIdentifier.asString())
        contact.save()
    }

    private void doHandle(ContactNameChangedEvent event) {
        ContactEntry foundContact = ContactEntry.findByIdentifier(event.aggregateIdentifier.asString())
        foundContact.name = event.newName
        foundContact.save()
    }

    private void doHandle(ContactDeletedEvent event) {
        ContactEntry foundContact = ContactEntry.findByIdentifier(event.aggregateIdentifier.asString())
        List<AddressEntry> foundAddresses = AddressEntry.findAllByContactName(foundContact.name)

        foundContact.delete()

        foundAddresses.each {address ->
            address.delete()
        }
    }

    private void doHandle(AddressAddedEvent event) {
        ContactEntry foundContact = ContactEntry.findByIdentifier(event.aggregateIdentifier.asString())
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

    private void doHandle(AddressRemovedEvent event) {
        ContactEntry foundContact = ContactEntry.findByIdentifier(event.aggregateIdentifier.asString())
        AddressEntry foundAddress = AddressEntry.findByAddressTypeAndContactIdentifier(event.addressType,foundContact.identifier)
        if (foundAddress) {
            foundAddress.delete()
        }
    }

    private void doHandle(AddressChangedEvent event) {
        ContactEntry foundContact = ContactEntry.findByIdentifier(event.aggregateIdentifier.asString())
        AddressEntry foundAddress = AddressEntry.findByAddressTypeAndContactIdentifier(event.addressType,foundContact.identifier)
        Address address = event.addedAddress
        foundAddress.contactName = foundContact.name
        foundAddress.contactIdentifier = foundContact.identifier
        foundAddress.streetAndNumber = address.streetAndNumber
        foundAddress.zipCode = address.zipCode
        foundAddress.city = address.city
        foundAddress.addressType = event.addressType
        foundAddress.save()
    }

}
