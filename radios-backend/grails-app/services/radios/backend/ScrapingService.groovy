package radios.backend

import Command.news.SaveCommand
import grails.gorm.transactions.Transactional
import org.jsoup.Jsoup

@Transactional
class ScrapingService {
    final String NEUQUEN_GOV = "http://w2.neuquen.gov.ar/actualidad/noticias"
    final String NEUQUEN_MUNI = "http://www.ciudaddeneuquen.gob.ar/prensa/"
    NewsService newsService

    def scrap() {
        def doc = Jsoup.connect(NEUQUEN_GOV).userAgent("Mozilla/5.0").get()
        def articles = doc.select("a.readon")
        articles.each { article ->
            def raw_web = Jsoup.connect(NEUQUEN_GOV + article.attr("href")).get()
            def raw_article = raw_web.select("#ja-current-content")
            String raw = raw_article.toString().replaceAll('/images', NEUQUEN_GOV.substring(0, (NEUQUEN_GOV.length() - 20)) + '/images')
            def a = newsService.save(
                new SaveCommand([
                    title         : raw_article.select('.contentheading span').text(),
                    description   : raw_article.select('.article-content p b i').text(),
                    rawDescription: raw,
                    newsCategory  : NewsCategory.first(),
                    radios        : [Radio.first()],
                    user          : User.first(),
                    image         : Multimedia.first().mediaId
                ]))
        }
        def doc2 = Jsoup.connect(NEUQUEN_MUNI.substring(0,NEUQUEN_MUNI.length() -1 )).userAgent("Mozilla/5.0").get()
        def articles2 = doc2.select(".entry-title")
        articles2.each { article ->
            def raw_web = Jsoup.connect( article.select('a').attr("href")).get()
            println(raw_web)
        }

    }
}
