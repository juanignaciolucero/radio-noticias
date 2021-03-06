package radios.backend

class News {
    String title
    String imageUrl
    String description
    String rawDescription
    Boolean featured
    String source
    Boolean enabled = true
    NewsCategory newsCategory
    User user
    Multimedia audio
    Multimedia image
    Date dateCreated
    Date lastUpdated
    String scraping

    static hasMany = [radios: Radio]

    static mapping = {
        autoTimestamp: true
        description type: 'text'
        rawDescription type: 'text'
    }

    static constraints = {
        title nullable: false, blank: false, maxSize: 255
        imageUrl nullable: true
        description nullable: true, blank: false
        rawDescription nullable: true
        featured nullable: false
        enabled nullable: false
        newsCategory nullable: false
        user nullable: false
        radios minSize: 1, nullable: false
        audio nullable: true
        image nullable: false
        source nullable: true
        scraping nullable: true
    }
}
