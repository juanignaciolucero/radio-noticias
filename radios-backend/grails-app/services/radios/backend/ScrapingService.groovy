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
        News.findByTitle('Neuquén se sumó al plan nacional de Derechos Humanos')
        def doc = Jsoup.connect(NEUQUEN_GOV).userAgent("Mozilla/5.0").get()
        def articles = doc.select("a.readon")
        articles.each { article ->
            def raw_web = Jsoup.connect(NEUQUEN_GOV + article.attr("href")).get()
            def raw_article = raw_web.select("#ja-current-content")
            String raw = raw_article.toString().replaceAll('/images', NEUQUEN_GOV.substring(0, (NEUQUEN_GOV.length() - 20)) + '/images')
            def title = raw_article.select('.contentheading span').text()
            if (News.findByTitle(title) == null) {
                def a = newsService.save(
                    new SaveCommand([
                        title         : title,
                        description   : raw_article.select('.article-content p b i').text(),
                        rawDescription: raw,
                        newsCategory  : NewsCategory.first(),
                        radios        : [Radio.first()],
                        user          : User.first(),
                        image         : Multimedia.first().mediaId
                    ]))
            }
        }
        def doc2 = Jsoup.connect(NEUQUEN_MUNI).userAgent("Mozilla/5.0").get()
        def articles2 = doc2.select(".entry-title")
        articles2.each { article ->





























































































































































            def raw_web = Jsoup.connect(article.select('a').attr("href")).get()
            def raw_article = raw_web.select('.post')
            def title = raw_article.select('.entry-title').text()
            if (News.findByTitle(title) == null) {
                def a = newsService.save(
                    new SaveCommand([
                        title         : title,
                        description   : raw_article.select('p').not(".wp-caption-text")[0].text(),
                        rawDescription: raw_article.select('p').text(),
                        newsCategory  : NewsCategory.findByName(raw_article.attr("class").substring(raw_article.attr("class").lastIndexOf("category-") + 9).replaceAll('-', ' ')),
                        radios        : [Radio.first()],
                        user          : User.first(),
                        image         : Multimedia.first().mediaId
                    ])
                )
            }
        }

    }
}
