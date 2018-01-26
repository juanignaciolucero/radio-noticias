package backend

class Noticia {
    String title
    String news
    Boolean destacada
    Boolean enable
    static belongsTo = [radios : Radio]
    static constraints = {
        title nulleable:false,blank:false
        news nulleable:false,blank:false
        destacada nulleable:false
        enable nulleable:false
    }
}
