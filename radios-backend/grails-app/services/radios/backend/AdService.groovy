package radios.backend

import grails.gorm.services.Service
import Command.ads.UpdateCommand

class AdService {

    Ad get(Serializable id) {
        return Ad.get(id)
    }

    List<Ad> list(Map args) {
        return Ad.findAllByRadio(Radio.get(args.radio_id))
    }

    Long count() {}

    Ad update(UpdateCommand command) {
        Ad ad = command.getAd()
        ad.properties = command.params()
        ad.validate()
        if (!ad.hasErrors()) {
            ad.save()
        }
        return ad

    }

    void delete(Serializable id) {}

    Ad save(Ad ad) {}

}
