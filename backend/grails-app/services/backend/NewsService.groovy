package backend

import grails.gorm.transactions.Transactional
import news.SaveCommand

@Transactional
class NewsService {

    News save(SaveCommand command) {
        News news = new News(command.params())
        news.validate()
        if(!news.hasErrors()) {
            news.save()
        }
        return news
    }
}
