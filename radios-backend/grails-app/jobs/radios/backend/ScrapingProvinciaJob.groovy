package radios.backend

class ScrapingProvinciaJob {
    def static concurrent = false
    ScrapingService scrapingService
    static triggers = {
      cron name:"scrapingProvincia", cronExpression: '0 0/30 * 1/1 * ? *'
    }

    def execute() {
        scrapingService.neuquenGov()
    }
}
