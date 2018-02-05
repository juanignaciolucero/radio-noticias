package radios.backend

import Command.multimedia.SaveCommand
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class MultimediaController {

    MultimediaService multimediaService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST"]

    def save(SaveCommand command) {
        if (!command.hasErrors()) {
            respond(multimediaService.save(command))
        } else {
            respond(command.errors)
        }
    }
}
