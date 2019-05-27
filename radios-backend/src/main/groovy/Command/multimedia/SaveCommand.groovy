package Command.multimedia

import enums.MultimediaType
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile
import utils.FileUtils

class SaveCommand implements Validateable {
    MultipartFile file
    MultimediaType type

    static constraints = {
        type nullable: true
        file validator: { file, obj ->
            return (file && !file.empty && obj.validate(file))
        }
    }

    Boolean validate(MultipartFile file) {
        String ext = FileUtils.getExt(file)
        type = MultimediaType.values().find { MultimediaType mt ->
            mt.types.contains(ext)
        }
        return type as Boolean
    }
}
