package radios.backend

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
class NewsCategoryController {

    NewsCategoryService newsCategoryService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond newsCategoryService.list(params), model:[newsCategoryCount: newsCategoryService.count()]
    }

    def show(Long id) {
        respond newsCategoryService.get(id)
    }

    def save(NewsCategory newsCategory) {
        if (newsCategory == null) {
            render status: NOT_FOUND
            return
        }

        try {
            newsCategoryService.save(newsCategory)
        } catch (ValidationException e) {
            respond newsCategory.errors, view:'create'
            return
        }

        respond newsCategory, [status: CREATED, view:"show"]
    }

    def update(NewsCategory newsCategory) {
        if (newsCategory == null) {
            render status: NOT_FOUND
            return
        }

        try {
            newsCategoryService.save(newsCategory)
        } catch (ValidationException e) {
            respond newsCategory.errors, view:'edit'
            return
        }

        respond newsCategory, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        newsCategoryService.delete(id)

        render status: NO_CONTENT
    }
}
