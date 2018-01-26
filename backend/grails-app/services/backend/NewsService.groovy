package backend

import grails.gorm.transactions.Transactional

@Transactional
class NewsService {

    News save(SaveCommand command) {
        News news = News(command.params())
        return news.save()
    }
}
