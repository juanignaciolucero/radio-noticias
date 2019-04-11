package radios.backend

import grails.core.GrailsApplication
import grails.plugin.awssdk.s3.AmazonS3Service
import radios.backend.ScrapingMunicipalidadJob
import radios.backend.ScrapingProvinciaJob
import radios.backend.ScrapingRadio10Job


class BootStrap {

    AdService adService
    AmazonS3Service amazonS3Service
    GrailsApplication grailsApplication
    def init = { servletContext ->
        Role role = Role.findOrCreateByAuthority("ROLE_ADMIN").save()
        User user = User.findByUsername("admin")
        if (!user) {
            user = new User([
                username : "admin",
                firstName: "ADMIN",
                lastName : "ADMIN",
                password : "radios1234!"
            ])
            user.save()
            UserRole.create(user, role)
        }

        Radio.findOrCreateByName('Radio 10').save()
        Radio.findOrCreateByName('Vale 91.7').save()
        NewsCategory.findOrCreateByName("Otros").save()

        adService.init()
        amazonS3Service.createBucket(grailsApplication.config.getProperty('aws.s3.bucket.name'))

        ScrapingMunicipalidadJob.triggerNow()
        ScrapingProvinciaJob.triggerNow()
        ScrapingRadio10Job.triggerNow()
    }
    def destroy = {
    }
}
