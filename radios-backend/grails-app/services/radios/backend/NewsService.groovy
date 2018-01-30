package radios.backend

import grails.gorm.transactions.Transactional

import news.IndexCommand
import news.SaveCommand

import news.UpdateCommand

@Transactional
class NewsService {

    News save(SaveCommand command) {
        News news = new News(command.params())
        news.validate()
        if (!news.hasErrors()) {
            news.save()
        }
        return news
    }

    List<News> index(IndexCommand command) {
        return News.createCriteria().list() {
            if (!command.showDisabled) {
                eq("enable", true)
            }
            if (command.radio) {
                radios {
                    eq("id", command.radio.id)
                }
            }
        }
    }

    def delete(News news) {
        news.enable = false
        news.save(failOnError: true)
    }

    News update(UpdateCommand command) {
        News news = command.getNews()
        news.properties = command.params()
        news.validate()
        if (!news.hasErrors()) {
            news.save()
        }
        return news
    }

}
