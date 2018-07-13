package radios.backend

class ScrapingJob {
    def static concurrent = false
    ScrapingService scrapingService
    static triggers = {
      cron name:"scraping", cronExpression: '0 0/30 * 1/1 * ? *'
    }

    def execute() {
        scrapingService.scrap()
    }
}
