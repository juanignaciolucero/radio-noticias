package news

import backend.NewsCategory
import backend.Radio
import backend.User

class SaveCommand implements grails.validation.Validateable  {
    String title
    String news
    Boolean featured
    Boolean enable
    List<Radio> radios
    NewsCategory newsCategory
    User user

    Map params() {
        return [
                title: title,
                news: news,
                featured: featured,
                enable: enable,
                newsCategory:newsCategory,
                radios: radios,
                user:user
        ]
    }
}
