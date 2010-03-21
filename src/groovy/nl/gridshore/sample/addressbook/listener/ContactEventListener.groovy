package nl.gridshore.sample.addressbook.listener

import nl.gridshore.sample.addressbook.Contact
import nl.gridshore.sample.addressbook.event.ContactCreatedEvent
import org.axonframework.core.Event
import nl.gridshore.sample.addressbook.event.ContactNameChangedEvent
import nl.gridshore.sample.addressbook.event.ContactDeletedEvent

/**
 * @author Jettro Coenradie
 */
class ContactEventListener implements org.axonframework.core.eventhandler.EventListener {

  def ContactEventListener(eventBus) {
    print "initialize the listener \n"
    eventBus.subscribe this
  }

  void handle(Event event) {
    print "Incoming event \n"

    if (event instanceof ContactCreatedEvent) {
      def contact = new Contact(name: event.name, identifier:event.aggregateIdentifier.toString())
      contact.save()
    } else if (event instanceof ContactNameChangedEvent) {
      Contact foundContact = Contact.findByIdentifier(event.aggregateIdentifier.toString())
      foundContact.name = event.newName
      foundContact.save()
    } else if (event instanceof ContactDeletedEvent) {
      Contact foundContact = Contact.findByIdentifier(event.aggregateIdentifier.toString())
      foundContact.delete()
    }
  }
}
