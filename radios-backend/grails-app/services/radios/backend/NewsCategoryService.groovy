package radios.backend

import grails.gorm.services.Service

@Service(NewsCategory)
interface NewsCategoryService {

    NewsCategory get(Serializable id)

    List<NewsCategory> list(Map args)

    Long count()

    void delete(Serializable id)

    NewsCategory save(NewsCategory newsCategory)

}