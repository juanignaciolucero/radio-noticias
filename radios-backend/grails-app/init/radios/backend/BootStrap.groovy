package radios.backend

import grails.core.GrailsApplication
import grails.plugin.awssdk.s3.AmazonS3Service
import radios.backend.ScrapingMunicipalidadJob
import radios.backend.ScrapingProvinciaJob
import radios.backend.ScrapingRadio10Job

class BootStrap {

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
        Radio radio = Radio.findOrCreateByName("RADIO10").save()
        Radio.findOrCreateByName("VALENOVENTAICIETESINCO").save()
        NewsCategory.findOrCreateByName("Otros").save()
        amazonS3Service.createBucket(grailsApplication.config.getProperty('aws.s3.bucket.name'))
        Ad ad = Ad.findByTabNameAndTabSection("provinciales y nacionales", "carrousel")
        if (!ad) {
            new Ad([
                    tabName   : "Provinciales y Nacionales",
                    tabSection: "carrousel",
                    type      : "multiple",
                    enabled   : true,
                    radio     : radio,
                    adMetadata: []
            ]).save(failOnError: true)
        }
        ScrapingMunicipalidadJob.triggerNow()
        ScrapingProvinciaJob.triggerNow()
        ScrapingRadio10Job.triggerNow()
    }
    def destroy = {
    }
}
