package news

import radios.backend.News

class UpdateCommand extends NewsHolder {
    Integer id

    News getNews() {
        return News.get(id)
    }
}
