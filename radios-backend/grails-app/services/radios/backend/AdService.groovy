package radios.backend

import grails.gorm.services.Service

@Service(Ad)
class AdService {

    Ad get(Serializable id){
        return Ad.get(id)
    }

    List<Ad> list(Map args){
        return Ad.findAllByRadio(Radio.get(args.radio_id))
    }

    Long count(){}

    void delete(Serializable id){}

    Ad save(Ad ad){}

}
