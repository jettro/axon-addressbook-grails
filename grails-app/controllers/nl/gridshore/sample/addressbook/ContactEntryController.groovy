package nl.gridshore.sample.addressbook

import nl.gridshore.sample.addressbook.contact.ContactCommandHandlerService

class ContactEntryController {
    static navigation = [
            group: 'tabs',
            order: 20,
            subItems: [
                    [group: 'tabs', action: 'create']
            ]
    ]

    def scaffold = ContactEntry
    ContactCommandHandlerService contactCommandHandlerService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", saveAddress: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def show = {
        def contactEntryInstance = ContactEntry.get(params.id)
        def contactIdentifier = contactEntryInstance.identifier
        def addressEntriesForContact = AddressEntry.findAllByContactIdentifier(contactIdentifier)

        [addressEntryInstanceList: addressEntriesForContact, contactEntryInstance: contactEntryInstance]
    }


    def save = {
        def contactEntryInstance = new ContactEntry(params)
        if (contactEntryInstance.validate()) {
            def uuid = contactCommandHandlerService.createContact(contactEntryInstance.name)
            def foundContact = ContactEntry.findByIdentifier(uuid)

            flash.message = "${message(code: 'default.created.message', args: [message(code: 'contactEntry.label', default: 'ContactEntry'), foundContact.name])}"
            redirect(action: "show", id: foundContact.id)
        }
        else {
            render(view: "create", model: [contactEntryInstance: contactEntryInstance])
        }
    }

    def update = {
        def contactEntryInstance = ContactEntry.get(params.id)
        if (contactEntryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (contactEntryInstance.version > version) {

                    contactEntryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'contactEntry.label', default: 'ContactEntry')] as Object[], "Another user has updated this ContactEntry while you were editing")
                    render(view: "edit", model: [contactEntryInstance: contactEntryInstance])
                    return
                }
            }
            contactEntryInstance.properties = params
            if (!contactEntryInstance.hasErrors() && contactEntryInstance.validate()) {
                contactCommandHandlerService.updateContactName(contactEntryInstance.identifier, contactEntryInstance.name)
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'contactEntry.label', default: 'ContactEntry'), contactEntryInstance.name])}"
                redirect(action: "show", id: contactEntryInstance.id)
            }
            else {
                render(view: "edit", model: [contactEntryInstance: contactEntryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contactEntry.label', default: 'ContactEntry'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def contactEntryInstance = ContactEntry.get(params.id)
        if (contactEntryInstance) {
            try {
                contactCommandHandlerService.removeContact(contactEntryInstance.identifier)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'contactEntry.label', default: 'ContactEntry'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'contactEntry.label', default: 'ContactEntry'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contactEntry.label', default: 'ContactEntry'), params.id])}"
            redirect(action: "list")
        }
    }

    def registerAddress = {
        def contactEntryInstance = ContactEntry.get(params.id)
        def addressEntryInstance = new AddressEntry();
        addressEntryInstance.contactIdentifier = contactEntryInstance.identifier
        addressEntryInstance.contactName = contactEntryInstance.name
        [addressEntryInstance: addressEntryInstance]
    }

    def saveAddress = {
        def addressEntryInstance = new AddressEntry(params)
        if (addressEntryInstance.validate()) {
            def foundContact = ContactEntry.findByIdentifier(addressEntryInstance.contactIdentifier)
            contactCommandHandlerService.registerAddress(
                    addressEntryInstance.addressType,
                    addressEntryInstance.contactIdentifier,
                    addressEntryInstance.streetAndNumber,
                    addressEntryInstance.zipCode,
                    addressEntryInstance.city)
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'addressEntry.label', default: 'AddressEntry'), addressEntryInstance.city])}"
            redirect(action: "show", params:[id:foundContact.id])
        }
        else {
            render(view: "registerAddress", model: [addressEntryInstance: addressEntryInstance])
        }
    }

    def deleteAddress = {
        def addressEntryInstance = AddressEntry.get(params.id)
        if (addressEntryInstance) {
            contactCommandHandlerService.removeAddress(addressEntryInstance.addressType, addressEntryInstance.contactIdentifier)
        }
        def foundContact = ContactEntry.findByIdentifier(addressEntryInstance.contactIdentifier)
        redirect(action: "show", id: foundContact.id)
    }

}
