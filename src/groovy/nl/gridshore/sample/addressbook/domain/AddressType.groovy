package nl.gridshore.sample.addressbook.domain

/**
 * @author Jettro Coenradie
 */
public enum AddressType {
    WORK('Work'),
    PRIVATE('Private'),
    VACATION('Vacation')


    String name

    AddressType(String name) {
        this.name = name
    }

    static list() {
        [WORK, PRIVATE, VACATION]
    }
}
