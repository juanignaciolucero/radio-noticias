package radios.backend

import enums.MultimediaType

class Multimedia {
    String mediaId
    MultimediaType type
    static constraints = {
        mediaId nullable: false
        type nullable: false
    }
}
