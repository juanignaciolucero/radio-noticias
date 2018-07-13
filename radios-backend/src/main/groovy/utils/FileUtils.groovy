package utils

import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.IOUtils
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile

class FileUtils {
    static String getExt(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename())
    }

    static File getFileFromUrl(String url) {
        File temp = File.createTempFile("download", ".jpg")
        BufferedOutputStream file = temp.newOutputStream()
        file << new URL(url).openStream()
        file.close()
        return temp
    }

    static MultipartFile fileToMultipart(File file) {
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
    }
}
