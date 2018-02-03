package Command.news

import enums.MultimediaType
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile
import radios.backend.News
import utils.FileUtils


class AddMultimediaCommand implements Validateable{
    MultipartFile file
    News news
    MultimediaType type
    static constrainst = {
        file validator: { file, obj, errors ->
            return(file && !file.empty && obj.validate(file, obj.type))
        }

    }
    Boolean validate(MultipartFile file, MultimediaType type) {
        String ext = FileUtils.getExt(file)
        switch (type) {
            case MultimediaType.AUDIO: return ['mp3'].contains(ext)
                break
            case MultimediaType.VIDEO: return ['jpg'].contains(ext)
                break
        }
    }
}
