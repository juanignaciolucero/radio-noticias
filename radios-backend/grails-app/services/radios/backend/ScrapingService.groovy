package radios.backend

import Command.news.SaveCommand
import Command.news.UpdateCommand
import enums.MultimediaType
import enums.Scraping
import grails.gorm.transactions.Transactional
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import utils.FileUtils


@Transactional
class ScrapingService {
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

    private findOrCreateCategory(String name, String bg, String text) {
        NewsCategory category = NewsCategory.findByName(name)
        if (!category) {
            category = new NewsCategory()
            category.name = name
            category.backgroundColor = bg
            category.textColor = text
            category.save()
        }
        return category
    }

    def neuquenGov() {
        Boolean featured = !newsService.completedFeaturedNewsPerDay()
        Document doc = Jsoup.connect(Scraping.NEUQUEN_GOV.base + Scraping.NEUQUEN_GOV.feed).userAgent("Mozilla/5.0").get()
        Elements articles = doc.select(".contentpaneopen")
        // Estas noticias no tienen categoria, se crea una nueva con el nombre de la pagina
        NewsCategory category = NewsCategory.findByName('Neuquén Provincia')
        if (!category) {
            category = new NewsCategory()
            category.name = 'Neuquén Provincia'
            category.backgroundColor = Scraping.NEUQUEN_GOV.background
            category.textColor = Scraping.NEUQUEN_GOV.font
            category.save()
        }

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
                        Scraping.NEUQUEN_GOV.base + Scraping.NEUQUEN_GOV.feed + article.select("a.readon").attr("href")
                ).get()

                // Seleccion del contenido de la noticia
                Elements raw_article = fullArticle.select(".article-content")

                String imageUrl = getImageUrl(
                        Scraping.NEUQUEN_GOV.base,
                        raw_article.select('img')[0].attr("src")
                )

                // elimina imagen y iconos innecesarios
                raw_article.select('p img').remove()
                raw_article.select('.article-toolswrap').remove()

                // Ajusta las url de imagenes y convierte la seleccion en String
                String raw = raw_article.toString().replaceAll('/images', Scraping.NEUQUEN_GOV.base + '/images')
                newsService.save(
                        new SaveCommand([
                                title         : title,
                                description   : description,
                                rawDescription: raw,
                                source        : Scraping.NEUQUEN_GOV.base,
                                featured      : featured,
                                newsCategory  : category,
                                radios        : Radio.all,
                                user          : User.first(),
                                image         : downloadUrlImage(imageUrl).mediaId,
                                scraping      : Scraping.NEUQUEN_GOV.toString()
                        ]), true, true)
                featured = (featured) ? false : featured
            }
        }
    }

    def neuquenMunicipalidad() {
        Boolean featured = !newsService.completedFeaturedNewsPerDay()
        Document doc = Jsoup.connect(Scraping.NEUQUEN_MUNI.base + Scraping.NEUQUEN_MUNI.feed).userAgent("Mozilla/5.0").get()
        Elements articles = doc.select("article.post")
        articles.each { def article ->
            String title = article.select('.entry-title').text()
            if (!News.findByTitle(title)) {
                NewsCategory category = findOrCreateCategory(
                        article.select('.entry-meta a')[2].text(),
                        '#1D3477',
                        '#ffffff')

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
                                source        : Scraping.NEUQUEN_MUNI.base,
                                rawDescription: raw_article.toString(),
                                newsCategory  : category,
                                radios        : Radio.all,
                                featured      : featured,
                                user          : User.first(),
                                image         : downloadUrlImage(img.attr("src")).mediaId,
                                scraping      : Scraping.NEUQUEN_MUNI.toString()
                        ]), true, true)
                featured = (featured) ? false : featured
            }
        }
    }

    Map minutounoRadio10news(Element article, Boolean featured) {
        String title = article.select(".title").text()
        String imageUrl = article.select("figure img").attr("src")
        NewsCategory category = NewsCategory.findOrCreateWhere(
                [name: article.selectFirst('.badge').attr('data-badge-caption')])
        String description = article.select(".preview a").text()
        def fullArticleRaw = new URL(article.select(".preview a").attr("href")).getText()
        Document fullArticle = Jsoup.parse(fullArticleRaw)
        // def raw_article = fullArticle.select('main.container')
        Elements raw_article = fullArticle.select('.detail-body')
        raw_article.select('.visitimg').remove()
        raw_article.select('.embed_options').remove()
        return [
                title         : title,
                description   : description,
                rawDescription: raw_article.toString(),
                source        : Scraping.MINUTO_UNO_RADIO10.base,
                newsCategory  : category,
                radios        : Radio.all,
                featured      : featured,
                user          : User.first(),
                imageUrl      : imageUrl,
                image         : downloadUrlImage(fullArticle.select('.media-wrapper img').attr("src")).mediaId,
                scraping      : Scraping.MINUTO_UNO_RADIO10.toString()
        ]
    }

    def minutoUnoRadio10() {
        Boolean featured = !newsService.completedFeaturedNewsPerDay()
        def sitio = new URL(Scraping.MINUTO_UNO_RADIO10.base + Scraping.MINUTO_UNO_RADIO10.feed).getText()
        Document doc = Jsoup.parse(sitio)
        Elements articles = doc.select(".note").not(".radio-mobile")
        articles.each { def article ->
            String title = article.select(".title").text().trim()
            String imageUrl = article.select("figure img").attr("src")
            News news = News.findByImageUrl(imageUrl)
            if (!news) {
                newsService.save(
                        new SaveCommand(minutounoRadio10news(article, featured)), true, true)
                featured = (featured) ? false : featured
            } else if (news.getTitle() != title) {
                UpdateCommand command = new UpdateCommand(minutounoRadio10news(article, news.featured))
                command.id = news.id
                newsService.update(command)
            }
        }
    }
}
