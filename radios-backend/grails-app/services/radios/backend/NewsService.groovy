package radios.backend

import Command.news.IndexCommand
import Command.news.SaveCommand
import Command.news.UpdateCommand
import commons.PaginateCommand
import grails.gorm.transactions.Transactional

@Transactional
class NewsService {
    CloudService cloudService
    News save(SaveCommand command) {
        News news = new News(command.params())
        news.validate()
        if (!news.hasErrors()) {
            news.save()
        }
        return news
    }

    List<News> index(IndexCommand command, PaginateCommand paginate) {
        return News.createCriteria().list(paginate.params()) {
            if (!command.showDisabled) {
                eq("enabled", true)
            }
            if (command.radio) {
                radios {
                    eq("id", command.radio.id)
                }
            }
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
}
