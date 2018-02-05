package utils

import org.apache.commons.io.FilenameUtils
import org.springframework.web.multipart.MultipartFile

class FileUtils {
    static String getExt(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename())
    }
}
