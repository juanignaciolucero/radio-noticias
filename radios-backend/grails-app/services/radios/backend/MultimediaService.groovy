package radios.backend

import Command.multimedia.SaveCommand
import grails.gorm.services.Service

@Service(Multimedia)
class MultimediaService {
    def cloudService

    Multimedia save(SaveCommand command) {
        Multimedia multimedia = new Multimedia(type: command.type)
        cloudService.upload(command.file, multimedia)
        multimedia.save(failOnError: true)
        return multimedia
    }
}