package radios.backend


import grails.rest.*
import grails.converters.*
import org.w3c.dom.Document
import org.w3c.dom.Element

class ScrapingController {
	static responseFormats = ['json', 'xml']
	
    def show() {
        Document doc = Jsoup.connect("http://w2.neuquen.gov.ar/actualidad/noticias").userAgent("Mozilla/5.0").timeout(100000).get();
        console.log(doc.select(""))
        Element articles = doc.select("a.readon");
        for (Element article : articles) {
            console.log("%s\n\t%s", articles.attr("href"), articles.absUrl("href"));
        }
    }
}
