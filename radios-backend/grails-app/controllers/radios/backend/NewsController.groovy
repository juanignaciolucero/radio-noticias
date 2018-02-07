package radios.backend

import Command.news.IndexCommand
import Command.news.SaveCommand
import Command.news.UpdateCommand
import commons.PaginateCommand
import grails.gorm.PagedResultList
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.http.HttpStatus

@Secured(['ROLE_ADMIN'])
class NewsController {
    static responseFormats = ['json']
    NewsService newsService
    @Secured('permitAll')
    def index(IndexCommand command, PaginateCommand paginate) {
        PagedResultList list = newsService.index(command, paginate)
        respond([news: list])
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
