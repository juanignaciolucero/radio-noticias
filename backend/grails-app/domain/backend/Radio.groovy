package backend

class Radio {
    String name
    static hasMany = [news : Noticia]
    static constraints = {
        name nulleable:false,blank:false
    }
}
