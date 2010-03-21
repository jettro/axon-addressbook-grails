import nl.gridshore.sample.addressbook.repository.ContactRepository
import org.axonframework.core.eventhandler.SimpleEventBus
import org.axonframework.core.repository.eventsourcing.XStreamEventSerializer
import org.axonframework.core.repository.eventsourcing.FileSystemEventStore

// Place your Spring DSL code here
beans = {
  contactRepository(ContactRepository) {
    eventBus = ref("eventBus")
    eventStore = ref("eventStore")
  }

  // axon beans
  eventBus(SimpleEventBus)

  eventStore(FileSystemEventStore, ref("xstreamSerializer")) {
    baseDir = "file:/Users/jcoenradie/temp/"
  }

  xstreamSerializer(XStreamEventSerializer)
}