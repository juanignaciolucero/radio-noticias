package radios.backend

class News {
    String title
    String description
    Boolean featured
    Boolean enabled = true
    NewsCategory newsCategory
    User user
    Multimedia audio
    Multimedia image
    Date dateCreated
    Date lastUpdated

    static hasMany = [radios: Radio]

    static mapping = {
        autoTimestamp: true
        description type: 'text'
    }

    static constraints = {
        title nullable: false, blank: false, maxSize: 255
        description nullable: false, blank: false
        featured nullable: false
        enabled nullable: false
        newsCategory nullable: false
        user nullable: false
        radios minSize: 1, nullable: false
        audio nullable: true
        image nullable: false
    }
}
