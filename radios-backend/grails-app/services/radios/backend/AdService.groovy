package radios.backend

import grails.gorm.services.Service

@Service(Ad)
interface AdService {

    Ad get(Serializable id)

    List<Ad> list(Map args)

    Long count()

    void delete(Serializable id)

    Ad save(Ad ad)

}