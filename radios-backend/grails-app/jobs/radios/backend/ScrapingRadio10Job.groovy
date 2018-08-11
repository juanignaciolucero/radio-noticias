package radios.backend

class ScrapingRadio10Job {
    def static concurrent = false
    ScrapingService scrapingService
    static triggers = {
        cron name:"scrapingRadio10", cronExpression: '0 0/30 * 1/1 * ? *'
    }

    def execute() {
        scrapingService.minutoUnoRadio10()
    }
}
