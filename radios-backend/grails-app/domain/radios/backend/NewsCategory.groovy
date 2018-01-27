package radios.backend

class NewsCategory {
    String name
    static constraints = {
        name nulleable:false, blank:false
    }
}
