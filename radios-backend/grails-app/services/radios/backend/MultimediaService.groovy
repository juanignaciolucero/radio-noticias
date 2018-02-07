package radios.backend

import Command.multimedia.SaveCommand
import grails.core.GrailsApplication
import grails.gorm.services.Service
import org.springframework.web.multipart.MultipartFile
import utils.FileUtils

class MultimediaService {
    def cloudService
    GrailsApplication grailsApplication

    Multimedia save(SaveCommand command) {
        Multimedia multimedia = new Multimedia(
                type: command.type,
                extension: FileUtils.getExt(command.file),
                name: command.file.originalFilename)
        cloudService.upload(command.file, multimedia)
        multimedia.save(failOnError: true)
        return multimedia
    }

    String getUrl(Multimedia multimedia) {
        return "${grailsApplication.config.getProperty('aws.s3.baseUrl')}/" +
                "${grailsApplication.config.getProperty('aws.s3.bucket.name')}/" +
                multimedia.getPath()
    }
}