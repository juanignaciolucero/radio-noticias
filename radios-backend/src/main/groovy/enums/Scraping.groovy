package enums

enum Scraping {
    NEUQUEN_GOV(
            'http://w2.neuquen.gov.ar',
            '/actualidad/noticias',
            '#1D3477',
            '#ffffff'
    ),
    NEUQUEN_MUNI(
            'http://www.ciudaddeneuquen.gob.ar',
            '/prensa/',
            '#f0d040',
            '#00000'
    ),
    MINUTO_UNO_RADIO10(
            'https://www.minutouno.com',
            '/radio10',
            '#2f3034',
            '#ffffff'
    )

    String base
    String feed
    String background
    String font

    Scraping(String base, String feed, String background, String font) {
        this.base = base
        this.feed = feed
        this.background = background
        this.font = font
    }
}