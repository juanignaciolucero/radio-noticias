package radios.backend

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NewsCategoryServiceSpec extends Specification {

    NewsCategoryService newsCategoryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NewsCategory(...).save(flush: true, failOnError: true)
        //new NewsCategory(...).save(flush: true, failOnError: true)
        //NewsCategory newsCategory = new NewsCategory(...).save(flush: true, failOnError: true)
        //new NewsCategory(...).save(flush: true, failOnError: true)
        //new NewsCategory(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //newsCategory.id
    }

    void "test get"() {
        setupData()

        expect:
        newsCategoryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NewsCategory> newsCategoryList = newsCategoryService.list(max: 2, offset: 2)

        then:
        newsCategoryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        newsCategoryService.count() == 5
    }

    void "test delete"() {
        Long newsCategoryId = setupData()

        expect:
        newsCategoryService.count() == 5

        when:
        newsCategoryService.delete(newsCategoryId)
        sessionFactory.currentSession.flush()

        then:
        newsCategoryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NewsCategory newsCategory = new NewsCategory()
        newsCategoryService.save(newsCategory)

        then:
        newsCategory.id != null
    }
}
