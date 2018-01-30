package news

import radios.backend.NewsCategory
import radios.backend.Radio
import radios.backend.User

class NewsHolder implements grails.validation.Validateable {
    String title
    String description
    Boolean featured
    Boolean enable
    List<Radio> radios
    NewsCategory newsCategory
    User user

    Map params() {
        return [
            title       : title,
            description : description,
            featured    : featured,
            enable      : enable,
            newsCategory: newsCategory,
            radios      : radios,
            user        : user
        ]
    }
}