package radios.backend

class ScrapingMunicipalidadJob {
    def static concurrent = false
    ScrapingService scrapingService
    static triggers = {
        cron name:"scrapingMunicipalidad", cronExpression: '0 0/30 * 1/1 * ? *'
    }

    def execute() {
        scrapingService.neuquenMunicipalidad()
    }
}
