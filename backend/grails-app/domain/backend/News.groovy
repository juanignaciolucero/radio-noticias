package backend

class News {
    String title
    String news
    Boolean featured
    Boolean enable
    NewsCategory newsCategory
    User user
    static hasMany = [radios : Radio]
    static constraints = {
        title nulleable:false,blank:false
        news nulleable:false,blank:false
        featured nulleable:false
        enable nulleable:false
        newsCategory nulleble:false
        user nulleable:false
    }
}
