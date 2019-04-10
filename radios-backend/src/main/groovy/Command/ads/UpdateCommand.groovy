package Command.ads

import grails.validation.Validateable
import radios.backend.Ad
import radios.backend.AdMetadata



class UpdateCommand implements Validateable {
    String id
    String tab_name
    String tab_section
    Boolean enabled
    String type
    List<AdMetadata> metadata = []

    Map params() {
        return [
            id         : id,
            enabled    : enabled,
            metadata   : metadata
        ]
    }

    Ad getAd(){
        return Ad.get(id)
    }
    static constraints =
        {
            id nullable: false
            tab_name nullable: false
            tab_section nullable: false
            type nullable: false, inList: ["multiple", "single"]
            metadata validator: { field, obj ->
                return (obj.type == "single" ? field.size < 2 : field.size < 6)
            }
        }
}
