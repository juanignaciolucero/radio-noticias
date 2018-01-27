package news

import backend.Radio
import grails.validation.Validateable

class IndexCommand implements Validateable{
    Radio radio
    static constraints = {
        radio nulleable:false
    }
}
