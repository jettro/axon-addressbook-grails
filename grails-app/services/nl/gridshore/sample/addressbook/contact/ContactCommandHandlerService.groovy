package nl.gridshore.sample.addressbook.contact

import nl.gridshore.sample.addressbook.domain.ContactAggregate
import nl.gridshore.sample.addressbook.repository.ContactRepository

class ContactCommandHandlerService {

  boolean transactional = true

  ContactRepository contactRepository

  def createContact(String contactName) {
    // TODO validate the contactName
    ContactAggregate contact = new ContactAggregate(contactName)
    contactRepository.save contact
    return contact.identifier.toString()
  }
}
