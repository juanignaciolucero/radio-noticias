package radios.backend
import news.IndexCommand
import news.SaveCommand

class NewsController {
	static responseFormats = ['json']
    NewsService newsService

    def index(IndexCommand command) {
        if(command.hasErrors()){
            response(command)
        }else{
            response(newsService.index())
        }
    }

    def show() {}

    def save(SaveCommand command) {
        respond(newsService.save(command))

    }

    def update() {}

    def delete() {}
}
