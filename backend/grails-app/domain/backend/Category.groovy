package backend

class Category {
    String name
    static constraints = {
        name nulleable:false, blank:false
    }
}
