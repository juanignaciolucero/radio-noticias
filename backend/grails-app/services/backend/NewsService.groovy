package backend

import grails.gorm.transactions.Transactional
import news.*

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

    List<News> index(IndexCommand command){
        return News.createCriteria().list(){
            radios {
                eq("id", command.radio.id)
            }
        }
    }
}
