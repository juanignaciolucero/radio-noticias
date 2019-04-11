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
        !idsToDelete ?: AdMetadata.findAllByIdInList(idsToDelete)*.delete()
        ad.properties = params
        ad.validate()
        if (!ad.hasErrors()) {
            ad.save(flush: true)
        }
        return ad

    }

    void delete(Serializable id) {}

    Ad save(Ad ad) {}

    void init() {
        Radio radio10 = Radio.findByName('Radio 10')
        Radio radioVale = Radio.findByName('Vale 91.7')
        List<Map> elements = [
            [
                tabName   : "Nacionales e Internacionales",
                tabSection: "Principal",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Nacionales e Internacionales",
                tabSection: "Banner Izquierdo 1",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Nacionales e Internacionales",
                tabSection: "Banner Izquierdo 2",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Nacionales e Internacionales",
                tabSection: "Banner Izquierdo 3",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Nacionales e Internacionales",
                tabSection: "Banner Izquierdo 4",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Nacionales e Internacionales",
                tabSection: "Secundario",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Nacionales e Internacionales",
                tabSection: "Carrousel",
                type      : "multiple",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Municipales",
                tabSection: "Carrousel",
                type      : "multiple",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Municipales",
                tabSection: "Principal",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Municipales",
                tabSection: "Banner Izquierdo",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Provinciales",
                tabSection: "Principal",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Provinciales",
                tabSection: "Banner Derecho 1",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ],
            [
                tabName   : "Provinciales",
                tabSection: "Banner Derecho 2",
                type      : "single",
                enabled   : true,
                radio     : radio10,
                adMetadata: []
            ]

        ]

        elements.each {
            Ad ad = Ad.findByTabNameAndTabSection(it.tabName, it.tabSection)
            if (!ad) {
                new Ad(it).save(failOnError: true)
            }
        }
    }

}
