package nl.gridshore.sample.addressbook

class ContactEntry {
    String name
    String identifier
    
    static constraints = {
        name(blank:false)
        identifier(blank:true, nullable:true)
    }
}
