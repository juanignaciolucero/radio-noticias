package radios.backend

class NewsCategory {
    String name
    static constraints = {
        name nullable:false, blank:false
    }
}
