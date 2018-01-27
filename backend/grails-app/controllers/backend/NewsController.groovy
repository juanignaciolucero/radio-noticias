package backend
import news.IndexCommand
import news.SaveCommand

class NewsController {
	static responseFormats = ['json', 'xml']
    NewsService newsService
    SsasasfService ssasasfService

    def index(IndexCommand command) {
        if(command.hasErrors()){
            response(command)
        }else{
            response(command.index())
        }
    }


    def show() {}

    def save(SaveCommand command) {
        respond(newsService.save(command))

    }

    def update() {}

    def delete() {}
}
