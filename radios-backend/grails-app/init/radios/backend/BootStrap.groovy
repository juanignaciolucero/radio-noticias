package radios.backend

import grails.core.GrailsApplication
import grails.plugin.awssdk.s3.AmazonS3Service

class BootStrap {
    AmazonS3Service amazonS3Service
    GrailsApplication grailsApplication
    def init = { servletContext ->
        Role role = Role.findOrCreateByAuthority("ROLE_ADMIN").save()
        User user = User.findByUsername("admin")
        if(!user){
            user = new User([
                    username:"admin",
                    firstName:"admino",
                    lastName:"aasdasd",
                    password:"1234"
            ])
            user.save()
            UserRole.create(user,role)
        }

        Radio.findOrCreateByName("VALENOVENTAICIETESINCO").save()
        NewsCategory.findOrCreateByName("Otros").save()
        try {
            amazonS3Service.createBucket(grailsApplication.config.getProperty('aws.s3.bucket.name'))
        } catch (e) {

        }
    }
    def destroy = {
    }
}
