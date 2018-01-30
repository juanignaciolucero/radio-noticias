package radios.backend

class News {
    String title
    String description
    Boolean featured
    Boolean enable = true
    NewsCategory newsCategory
    User user
    static hasMany = [radios: Radio]
    static constraints = {
        title nullable: false, blank: false
        description nullable: false, blank: false
        featured nullable: false
        enable nullable: false
        newsCategory nullable: false
        user nullable: false
        radios minSize: 1, nullable: false
    }
}
