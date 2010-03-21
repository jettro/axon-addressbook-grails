package nl.gridshore.sample.addressbook

class Contact {
  String name
  String identifier

  static constraints = {
    name(blank:false)
  }
}
