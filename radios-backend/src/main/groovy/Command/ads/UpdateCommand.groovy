package Command.ads

import grails.validation.Validateable
import radios.backend.Ad
import radios.backend.AdMetadata
import radios.backend.Multimedia


class UpdateCommand implements Validateable {
    String id
    String tabName
    String tabSection
    Boolean enabled
    String type
    List<AdMetadata> metadata = []

    Map params() {


        return [
            id         : id,
            enabled    : enabled,
            metadata   : metadata.collect {
                [
                        id: it.id,
                        urlRedirect: it.urlRedirect,
                        image: Multimedia.findByMediaId(it.image.mediaId)
                ]
            }
        ]
    }

    Ad getAd(){
        return Ad.get(id)
    }
    static constraints =
        {
            id nullable: false
            tabName nullable: false
            tabSection nullable: false
            type nullable: false, inList: ["multiple", "single"]
            metadata validator: { field, obj ->
                return (obj.type == "single" ? field.size < 2 : field.size < 6)
            }
        }
}
