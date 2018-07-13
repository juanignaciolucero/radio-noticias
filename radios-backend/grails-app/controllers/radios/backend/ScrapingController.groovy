package radios.backend

import grails.plugin.springsecurity.annotation.Secured

class ScrapingController {
	static responseFormats = ['json', 'xml']
	def scrapingService
    @Secured(['permitAll'])
    def scrap() {
        scrapingService.scrap()
    }
}
