package radios.backend

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured('ROLE_ADMIN')
class RadioController {

    RadioService radioService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond radioService.list(params), model:[radioCount: radioService.count()]
    }

    def show(Long id) {
        respond radioService.get(id)
    }

    def save(Radio radio) {
        if (radio == null) {
            render status: NOT_FOUND
            return
        }

        try {
            radioService.save(radio)
        } catch (ValidationException e) {
            respond radio.errors, view:'create'
            return
        }

        respond radio, [status: CREATED, view:"show"]
    }

    def update(Radio radio) {
        if (radio == null) {
            render status: NOT_FOUND
            return
        }

        try {
            radioService.save(radio)
        } catch (ValidationException e) {
            respond radio.errors, view:'edit'
            return
        }

        respond radio, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        radioService.delete(id)

        render status: NO_CONTENT
    }
}
