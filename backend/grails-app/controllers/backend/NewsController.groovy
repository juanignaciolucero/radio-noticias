package backend
import news.*
import grails.rest.*
import grails.converters.*

class NewsController {
	static responseFormats = ['json', 'xml']
	def newsService

    def index() {

    }

    def save(SaveCommand command) {
        Noticia news = newsService.save(command)


    }

    def edit() {}

    def delete() {}
}
