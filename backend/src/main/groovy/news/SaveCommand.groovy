package news

import backend.Radio

class SaveCommand implements grails.validation.Validateable  {
    String title
    String news
    Boolean destacada
    Boolean enable
    Radio radio

    Map params() {
        return [
                title: title,
                news: news,
                destacada: destacada,
                enable: enable,
                radio: radio
        ]
    }
}
