package radios.backend

import enums.MultimediaType

class Multimedia {
    String mediaId = UUID.randomUUID().toString()
    MultimediaType type
    String extension
    String name
    static constraints = {
        mediaId nullable: false
        type nullable: false
        extension nullable: false
        name nullable: false
    }
}
