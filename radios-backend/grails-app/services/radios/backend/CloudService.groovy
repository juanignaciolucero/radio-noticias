package radios.backend

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.plugin.awssdk.s3.AmazonS3Service
import org.springframework.web.multipart.MultipartFile

@Transactional
class CloudService {
    AmazonS3Service amazonS3Service
    GrailsApplication grailsApplication

    def upload(MultipartFile file, Multimedia multimedia) {
        amazonS3Service.storeMultipartFile(
            grailsApplication.config.getProperty('aws.s3.bucket.name'),
            multimedia.getPath(),
            file
        )
    }
}