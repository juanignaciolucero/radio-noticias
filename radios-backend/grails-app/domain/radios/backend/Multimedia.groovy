package radios.backend

import enums.MultimediaType

class Multimedia {
    String mediaId = UUID.randomUUID().toString()
    MultimediaType type
    static constraints = {
        mediaId nullable: false
        type nullable: false
    }
}
