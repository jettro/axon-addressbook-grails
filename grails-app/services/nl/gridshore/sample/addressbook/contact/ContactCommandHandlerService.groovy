package nl.gridshore.sample.addressbook.contact

import nl.gridshore.sample.addressbook.domain.Address
import nl.gridshore.sample.addressbook.domain.AddressType
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

    def updateContactName(String identifier, String newContactName) {
        // TODO validate incoming parameters
        ContactAggregate contact = contactRepository.load(UUID.fromString(identifier))
        contact.changeName newContactName
        contactRepository.save contact
    }

    def removeContact(String identifier) {
        // TODO validate identifier
        ContactAggregate contact = contactRepository.load(UUID.fromString(identifier))
        contact.delete()
        contactRepository.save contact
    }

    def registerAddress(AddressType addressType, String identifier, String streetNumber, String zipCode, String city) {
        // TODO add validation
        ContactAggregate contact = contactRepository.load(UUID.fromString(identifier))
        contact.registerAddress(addressType, new Address(city, streetNumber, zipCode))
        contactRepository.save contact
    }
}
