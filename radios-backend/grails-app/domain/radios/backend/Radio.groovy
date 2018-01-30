package radios.backend

class Radio {
    String name
    static constraints = {
        name nullable: false, blank: false
    }
}
