package radios.backend

import Command.news.IndexCommand
import Command.news.SaveCommand
import Command.news.UpdateCommand
import commons.PaginateCommand
import grails.gorm.transactions.Transactional

@Transactional
class NewsService {

    News save(SaveCommand command, Boolean failOnError = false, Boolean flush = false) {
        News news = new News(command.params())
        news.save(failOnError: failOnError, flush: flush)
        return news
    }

    List<News> index(IndexCommand command, PaginateCommand paginate) {
        return News.createCriteria().list(paginate.params()) {
            if (!command.showDisabled) {
                eq("enabled", true)
            }
            if (command.featured != null) {
                eq("featured", command.featured)
            }
            if (command.radio) {
                radios {
                    eq("id", command.radio.id)
                }
            }
            order('dateCreated', 'desc')
        }
    }

    def delete(News news) {
        news.enabled = false
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

    Boolean completedFeaturedNewsPerDay() {
        return News.createCriteria().list(max: 5) {
            eq("featured", true)
            ge("dateCreated", new Date().clearTime())
        }.size() == 5
    }
}
