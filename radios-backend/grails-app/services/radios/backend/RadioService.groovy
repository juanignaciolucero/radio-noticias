package radios.backend

import grails.gorm.services.Service

@Service(Radio)
interface RadioService {

    Radio get(Serializable id)

    List<Radio> list(Map args)

    Long count()

    void delete(Serializable id)

    Radio save(Radio radio)

}