package nl.gridshore.sample.addressbook.domain

/**
 * @author Jettro Coenradie
 */
class Address {
    final String streetAndNumber;
    final String zipCode;
    final String city;

    def Address(city, streetAndNumber, zipCode) {
        this.city = city;
        this.streetAndNumber = streetAndNumber;
        this.zipCode = zipCode;
    }


}
