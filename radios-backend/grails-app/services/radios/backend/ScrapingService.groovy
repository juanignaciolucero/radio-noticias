package radios.backend

import Command.news.SaveCommand
import enums.MultimediaType
import grails.gorm.transactions.Transactional
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import utils.FileUtils


@Transactional
class ScrapingService {
    Map NEUQUEN_GOV = [
        base: 'http://w2.neuquen.gov.ar',
        feed: '/actualidad/noticias'
    ]
    Map NEUQUEN_MUNI = [
        base: 'http://www.ciudaddeneuquen.gob.ar',
        feed: '/prensa/'
    ]
    Map MINUTO_UNO_RADIO10 = [
        base: 'https://www.minutouno.com',
        feed: '/radio10'
    ]

    NewsService newsService
    MultimediaService multimediaService

    /**
     * Ajusta la url de la imagen, en algunos casos la imagen viene con url relativa y en otros url absoluta.
     * @param base
     * @param articleUrl
     * @return
     */
    private String getImageUrl(String base, String articleUrl) {
        String result = articleUrl
        if (articleUrl.startsWith('/')) {
            result = base + articleUrl
        }
        return result
    }

    def scrap() {
        minutoUnoRadio10()
        neuquenGov()
        neuquenMunicipalidad()
    }

    private downloadUrlImage(String imageUrl) {
        // Descarga imagen de la noticia a un archivo temporal
        File file = FileUtils.getFileFromUrl(imageUrl)

        Command.multimedia.SaveCommand multimediaCommand = new Command.multimedia.SaveCommand([
            file: FileUtils.fileToMultipart(file), // Convierte el archivo en Multipart
            type: MultimediaType.IMAGE
        ])
        Multimedia multimedia = multimediaService.save(multimediaCommand)
        file.delete() // Elimina archivo temporal
        return multimedia
    }

    def neuquenGov() {
        Boolean featured = !newsService.completedFeaturedNewsPerDay()
        Document doc = Jsoup.connect(NEUQUEN_GOV.base + NEUQUEN_GOV.feed).userAgent("Mozilla/5.0").get()
        Elements articles = doc.select(".contentpaneopen")
        // Estas noticias no tienen categoria, se crea una nueva con el nombre de la pagina
        NewsCategory category = NewsCategory.findOrCreateWhere([name: 'Neuquén Provincia'])
        articles.each { def article ->
            String title = article.select('.contentheading').text()
            if (!News.findByTitle(title)) {
                // Obtiene la descripcion , en algunos casos la descripcion no tiene un span viene directament en <p>
                String description = article.select('.article-content p span').text()
                if (!description) {
                    description = article.select('.article-content p')[1].text()
                }

                // Obtiene la noticia completa
                Document fullArticle = Jsoup.connect(
                    NEUQUEN_GOV.base + NEUQUEN_GOV.feed + article.select("a.readon").attr("href")
                ).get()

                // Seleccion del contenido de la noticia
                Elements raw_article = fullArticle.select(".article-content")

                String imageUrl = getImageUrl(
                    NEUQUEN_GOV.base,
                    raw_article.select('img')[0].attr("src")
                )

                // elimina imagen y iconos innecesarios
                raw_article.select('p img').remove()
                raw_article.select('.article-toolswrap').remove()

                // Ajusta las url de imagenes y convierte la seleccion en String
                String raw = raw_article.toString().replaceAll('/images', NEUQUEN_GOV.base + '/images')
                newsService.save(
                    new SaveCommand([
                        title         : title,
                        description   : description,
                        rawDescription: raw,
                        source        : NEUQUEN_GOV.base,
                        featured      : featured,
                        newsCategory  : category,
                        radios        : Radio.all,
                        user          : User.first(),
                        image         : downloadUrlImage(imageUrl).mediaId
                    ]), true, true)
                featured = (featured) ? false : featured
            }
        }
    }

    def neuquenMunicipalidad() {
        Boolean featured = !newsService.completedFeaturedNewsPerDay()
        Document doc = Jsoup.connect(NEUQUEN_MUNI.base + NEUQUEN_MUNI.feed).userAgent("Mozilla/5.0").get()
        Elements articles = doc.select("article.post")
        articles.each { def article ->
            String title = article.select('.entry-title').text()
            if (!News.findByTitle(title)) {
                NewsCategory category = NewsCategory.findOrCreateWhere([name: article.select('.entry-meta a')[2].text()])
                // Obtiene la noticia completa
                Document fullArticle = Jsoup.connect(
                    article.select(".entry-title a").attr("href")
                ).get()

                Element description = article.select('p')[1]
                Element img = article.selectFirst('img')
                if (!img) {
                    description = article.selectFirst('p')
                    img = fullArticle.selectFirst('img')
                }

                // Seleccion del contenido de la noticia
                Element raw_article = fullArticle.selectFirst("article")

                // elimina imagen y secciones innecesarias
                raw_article.select('.entry-title').remove()
                raw_article.select('.hr-breadcrumbs').remove()
                if (!raw_article.select('.wp-caption')) {
                    raw_article.selectFirst('img').remove()
                } else {
                    raw_article.select('.wp-caption').remove()
                }
                raw_article.select('.sharedaddy').remove()
                raw_article.select('.wf-table').remove()
                raw_article.select('.navigation-inner, .gap-30, .hr-thick, gap-20, .entry-title, .gap-10, .items-grid').remove()

                newsService.save(
                    new SaveCommand([
                        title         : title,
                        description   : description.text(),
                        source        : NEUQUEN_MUNI.base,
                        rawDescription: raw_article.toString(),
                        newsCategory  : category,
                        radios        : Radio.all,
                        featured      : featured,
                        user          : User.first(),
                        image         : downloadUrlImage(img.attr("src")).mediaId
                    ]), true, true)
                featured = (featured) ? false : featured
            }
        }
    }

    def minutoUnoRadio10() {
        Boolean featured = !newsService.completedFeaturedNewsPerDay()
        def sitio = new URL(MINUTO_UNO_RADIO10.base + MINUTO_UNO_RADIO10.feed).getText()
        Document doc = Jsoup.parse(sitio)
        Elements articles = doc.select(".note").not(".radio-mobile")
        articles.each { def article ->
            String title = article.select(".title").text()
            String imageUrl = article.select("figure img").attr("src")
            if (!News.findByTitle(title) && !News.findByImageUrl(imageUrl)) {
                NewsCategory category = NewsCategory.findOrCreateWhere(
                    [name: article.selectFirst('.badge').attr('data-badge-caption')])
                String description = article.select(".preview a").text()
                def fullArticleRaw = new URL(article.select(".preview a").attr("href")).getText()
                Document fullArticle = Jsoup.parse(fullArticleRaw)
                // def raw_article = fullArticle.select('main.container')
                Elements raw_article = fullArticle.select('.detail-body')
                raw_article.select('.visitimg').remove()
                raw_article.select('.embed_options').remove()

                newsService.save(
                    new SaveCommand([
                        title         : title,
                        description   : description,
                        rawDescription: raw_article.toString(),
                        source        : MINUTO_UNO_RADIO10.base,
                        newsCategory  : category,
                        radios        : Radio.all,
                        featured      : featured,
                        user          : User.first(),
                        imageUrl      : imageUrl,
                        image         : downloadUrlImage(fullArticle.select('.media-wrapper img').attr("src")).mediaId
                    ]), true, true)
                featured = (featured) ? false : featured
            }
        }
    }

}
