package radios.backend

import commons.PaginateCommand
import grails.plugin.springsecurity.annotation.Secured
import news.IndexCommand
import news.SaveCommand

import news.UpdateCommand
import org.springframework.http.HttpStatus

@Secured('ROLE_ADMIN')
class NewsController {
    static responseFormats = ['json']
    NewsService newsService

    def index(IndexCommand command, PaginateCommand paginate) {
        respond([news: newsService.index(command,paginate)])
    }

    def show(News news) {
        if (news) {
            respond(news)
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value())
            respond([:])
        }
    }

    def save(SaveCommand command) {
        respond(newsService.save(command))
    }

    def update(UpdateCommand command) {
        respond(newsService.update(command))
    }

    def delete(News news) {
        if (news && news.enabled) {
            newsService.delete(news)
            respond([:])
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value())
            respond([:])
        }
    }
}
