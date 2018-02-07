package radios.backend

class Ad {
    String title
    Multimedia multimedia
    Date dateCreated
    Date lastUpdated

    static constraints = {
    }
    static mapping = {
        autoTimestamp: true
    }
}
