package Command.news

import radios.backend.Multimedia
import radios.backend.NewsCategory
import radios.backend.Radio
import radios.backend.User

class NewsHolder implements grails.validation.Validateable {
    String title
    String description
    Boolean featured = false
    Boolean enabled
    String audio
    String image
    List<Radio> radios
    NewsCategory newsCategory
    User user

    Map params() {
        return [
                title       : title,
                description : description,
                featured    : featured,
                enabled     : enabled,
                newsCategory: newsCategory,
                radios      : radios,
                user        : user,
                audio       : Multimedia.findByMediaId(audio),
                image       : Multimedia.findByMediaId(image)
        ]
    }
}