package radios.backend

class NewsCategory {
    String name
    String backgroundColor
    String textColor
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable:false, blank:false
        backgroundColor nullable: true
        textColor nullable: true
    }

    static mapping = {
        autoTimestamp: true
    }
}
