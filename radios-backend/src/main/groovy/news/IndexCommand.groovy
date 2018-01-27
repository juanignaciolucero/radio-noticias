package news

import grails.validation.Validateable
import radios.backend.Radio

class IndexCommand implements Validateable{
    Radio radio
    static constraints = {
        radio nulleable:false
    }
}