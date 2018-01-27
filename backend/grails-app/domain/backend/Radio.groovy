package backend

class Radio {
    String name
    static hasMany = [news : News]
    static constraints = {
        name nulleable:false,blank:false
    }
}
