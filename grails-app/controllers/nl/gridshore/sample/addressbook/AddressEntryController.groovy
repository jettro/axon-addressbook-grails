package nl.gridshore.sample.addressbook

import nl.gridshore.sample.addressbook.contact.ContactCommandHandlerService

class AddressEntryController {
    def scaffold = AddressEntry
    ContactCommandHandlerService contactCommandHandlerService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [addressEntryInstanceList: AddressEntry.list(params), addressEntryInstanceTotal: AddressEntry.count()]
    }

    def create = {
        def addressEntryInstance = new AddressEntry()
        addressEntryInstance.properties = params
        return [addressEntryInstance: addressEntryInstance]
    }

    def save = {
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
            redirect(action: "list")
        }
        else {
            render(view: "create", model: [addressEntryInstance: addressEntryInstance])
        }
    }

    def show = {
        def addressEntryInstance = AddressEntry.get(params.id)
        if (!addressEntryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'addressEntry.label', default: 'AddressEntry'), params.id])}"
            redirect(action: "list")
        }
        else {
            [addressEntryInstance: addressEntryInstance]
        }
    }

    def edit = {
        def addressEntryInstance = AddressEntry.get(params.id)
        if (!addressEntryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'addressEntry.label', default: 'AddressEntry'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [addressEntryInstance: addressEntryInstance]
        }
    }

    def update = {
        def addressEntryInstance = AddressEntry.get(params.id)
        if (addressEntryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (addressEntryInstance.version > version) {

                    addressEntryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'addressEntry.label', default: 'AddressEntry')] as Object[], "Another user has updated this AddressEntry while you were editing")
                    render(view: "edit", model: [addressEntryInstance: addressEntryInstance])
                    return
                }
            }
            addressEntryInstance.properties = params
            if (!addressEntryInstance.hasErrors() && addressEntryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'addressEntry.label', default: 'AddressEntry'), addressEntryInstance.id])}"
                redirect(action: "show", id: addressEntryInstance.id)
            }
            else {
                render(view: "edit", model: [addressEntryInstance: addressEntryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'addressEntry.label', default: 'AddressEntry'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def addressEntryInstance = AddressEntry.get(params.id)
        if (addressEntryInstance) {
            try {
                addressEntryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'addressEntry.label', default: 'AddressEntry'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'addressEntry.label', default: 'AddressEntry'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'addressEntry.label', default: 'AddressEntry'), params.id])}"
            redirect(action: "list")
        }
    }
}
