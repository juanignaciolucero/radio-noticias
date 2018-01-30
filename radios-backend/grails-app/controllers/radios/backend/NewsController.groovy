package radios.backend

import news.IndexCommand
import news.SaveCommand

import news.UpdateCommand
import org.springframework.http.HttpStatus

class NewsController {
    static responseFormats = ['json']
    NewsService newsService

    def index(IndexCommand command) {
        respond([news: newsService.index(command)])
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
        if (news && news.enable) {
            newsService.delete(news)
            respond([:])
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value())
            respond([:])
        }
    }
}
