package nl.gridshore.sample.addressbook

import nl.gridshore.sample.addressbook.contact.ContactCommandHandlerService

class ContactFormController {
  ContactCommandHandlerService contactCommandHandlerService

  def index = {
    def uuid = contactCommandHandlerService.createContact("Jettro")
    [uuid: uuid]
  }
}
