package Command.news

import radios.backend.Multimedia
import radios.backend.NewsCategory
import radios.backend.Radio
import radios.backend.User

class NewsHolder implements grails.validation.Validateable {
    String title
    String description
    String source
    String rawDescription
    Boolean featured = false
    Boolean enabled = true
    String audio
    String image
    String imageUrl
    List<Radio> radios
    NewsCategory newsCategory
    User user

    Map params() {
        return [
            title         : title,
            description   : description,
            source        : source,
            rawDescription: rawDescription,
            featured      : featured,
            enabled       : enabled,
            newsCategory  : newsCategory,
            radios        : radios,
            user          : user,
            audio         : Multimedia.findByMediaId(audio),
            image         : Multimedia.findByMediaId(image),
            imageUrl      : imageUrl
        ]
    }
}