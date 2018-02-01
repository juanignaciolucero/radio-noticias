package news

import radios.backend.NewsCategory
import radios.backend.Radio
import radios.backend.User

class NewsHolder implements grails.validation.Validateable {
    String title
    String description
    Boolean featured
    Boolean enabled
    List<Radio> radios
    NewsCategory newsCategory
    User user

    Map params() {
        return [
            title       : title,
            description : description,
            featured    : featured,
            enabled      : enabled,
            newsCategory: newsCategory,
            radios      : radios,
            user        : user
        ]
    }
}