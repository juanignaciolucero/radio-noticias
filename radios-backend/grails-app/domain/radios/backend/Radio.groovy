package radios.backend

class Radio {
    String name
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false
    }

    static mapping = {
        autoTimestamp: true
    }
}
