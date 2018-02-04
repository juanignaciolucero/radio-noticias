package radios.backend

import Command.multimedia.SaveCommand
import grails.gorm.services.Service
import utils.FileUtils

@Service(Multimedia)
class MultimediaService {
    def cloudService

    Multimedia save(SaveCommand command) {
        Multimedia multimedia = new Multimedia(
                type: command.type,
                extension: FileUtils.getExt(command.file),
                name: command.file.originalFilename)
        cloudService.upload(command.file, multimedia)
        multimedia.save(failOnError: true)
        return multimedia
    }
}