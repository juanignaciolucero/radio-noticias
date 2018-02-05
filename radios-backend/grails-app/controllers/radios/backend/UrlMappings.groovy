package radios.backend

class UrlMappings {

    static mappings = {
       /* delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")

        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')*/

        group("/api") {
            "/news"(resources: "news")
            "/radios"(resources: "radio")
            "/categories"(resources: "newsCategory")
            "/multimedia"(resources: "multimedia")
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
