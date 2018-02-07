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

    String getPath() {
        return "$type/${mediaId.substring(0, 4)}/$mediaId.$extension"
    }

    static String getUrl(Multimedia multimedia) {
        MultimediaService multimediaService = grails.util.Holders.applicationContext.getBean('multimediaService') as MultimediaService
        return multimediaService.getUrl(multimedia)
    }
}