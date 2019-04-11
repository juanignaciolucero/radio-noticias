package radios.backend

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
        Map params = command.params()
        List idsToDelete = (ad.metadata*.id - params.metadata*.id)
        !idsToDelete?: AdMetadata.findAllByIdInList(idsToDelete)*.delete()
        ad.properties = params
        ad.validate()
        if (!ad.hasErrors()) {
            ad.save(flush: true)
        }
        return ad

    }

    void delete(Serializable id) {}

    Ad save(Ad ad) {}

}
