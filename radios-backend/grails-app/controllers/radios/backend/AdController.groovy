package radios.backend

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
class AdController {

    AdService adService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('permitAll')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond adService.list(params), model:[adCount: adService.count()]
    }

    @Secured('permitAll')
    def show(Long id) {
        respond adService.get(id)
    }
    def save(Ad ad) {
        if (ad == null) {
            render status: NOT_FOUND
            return
        }

        try {
            adService.save(ad)
        } catch (ValidationException e) {
            respond ad.errors, view:'create'
            return
        }

        respond ad, [status: CREATED, view:"show"]
    }

    def update(Ad ad) {
        if (ad == null) {
            render status: NOT_FOUND
            return
        }

        try {
            adService.save(ad)
        } catch (ValidationException e) {
            respond ad.errors, view:'edit'
            return
        }

        respond ad, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        adService.delete(id)

        render status: NO_CONTENT
    }
}
