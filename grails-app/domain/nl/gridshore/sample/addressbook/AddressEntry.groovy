package nl.gridshore.sample.addressbook

import nl.gridshore.sample.addressbook.domain.AddressType

class AddressEntry {
    String contactName
    String contactIdentifier

    AddressType addressType
    String streetAndNumber
    String zipCode
    String city

    static constraints = {

    }
}
