package backend
import news.*
import grails.rest.*
import grails.converters.*

class NewsController {
	static responseFormats = ['json', 'xml']
    NewsService newsService
    SsasasfService ssasasfService

    def index() {

    }

    def show() {}

    def save(SaveCommand command) {
        News news = newsService.save(command)
        respond(news)

    }

    def update() {}

    def delete() {}
}
