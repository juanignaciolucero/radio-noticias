package radios.backend

import enums.MultimediaType
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.plugin.awssdk.s3.AmazonS3Service
import org.apache.commons.io.FilenameUtils
import org.springframework.web.multipart.MultipartFile

@Transactional
class CloudService {
    GrailsApplication grailsApplication
    AmazonS3Service amazonS3Service

    def upload(MultipartFile file, Multimedia multimedia) {
        if (file && !file.empty && validate(file, multimedia.type)) {
            amazonS3Service.storeMultipartFile(
                grailsApplication.getConfig('aws.s3.bucket.name'),
                getPath(multimedia, getExt(file)),
                file)
        }
    }

    private String getPath(Multimedia media, String ext) {
        return "${media.type}/${media.mediaId.substring(0, 4)}/${media.mediaId}.$ext"
    }

    private Boolean validate(MultipartFile file, MultimediaType type) {
        String ext = getExt(file)
        switch (type) {
            case MultimediaType.AUDIO: return ['mp3'].contains(ext)
                break
            case MultimediaType.VIDEO: return ['jpg'].contains(ext)
                break
        }
    }

    private String getExt(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename())
    }
}