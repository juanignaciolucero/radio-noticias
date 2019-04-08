package radios.backend

class AdMetadata {
    String urlRedirect
    Multimedia image


    static belongsTo = [ad: Ad]
    static constraints = {
        image nullable : true
    }
}
