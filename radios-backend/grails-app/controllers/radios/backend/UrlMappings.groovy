package radios.backend

class UrlMappings {

    static mappings = {
        group("/api") {
            "/news"(resources: "news")
            "/radios"(resources: "radio")
            "/categories"(resources: "newsCategory")
            "/multimedia"(resources: "multimedia")
            "/section_images"(resources: "ad")
            "/scrap"(controller: "Scraping", action: "scrap")
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
