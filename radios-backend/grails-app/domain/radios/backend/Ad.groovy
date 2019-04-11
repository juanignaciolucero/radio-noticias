package radios.backend

class Ad {
    String type
    String tabName
    String tabSection
    Boolean enabled
    Date dateCreated
    Date lastUpdated

    static hasMany = [metadata: AdMetadata]
    static belongsTo = [radio: Radio]

    static constraints = {
    }
    static mapping = {
        autoTimestamp: true
    }
}
