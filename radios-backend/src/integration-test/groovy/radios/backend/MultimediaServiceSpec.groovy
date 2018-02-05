package radios.backend

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MultimediaServiceSpec extends Specification {

    MultimediaService multimediaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Multimedia(...).save(flush: true, failOnError: true)
        //new Multimedia(...).save(flush: true, failOnError: true)
        //Multimedia multimedia = new Multimedia(...).save(flush: true, failOnError: true)
        //new Multimedia(...).save(flush: true, failOnError: true)
        //new Multimedia(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //multimedia.id
    }

    void "test get"() {
        setupData()

        expect:
        multimediaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Multimedia> multimediaList = multimediaService.list(max: 2, offset: 2)

        then:
        multimediaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        multimediaService.count() == 5
    }

    void "test delete"() {
        Long multimediaId = setupData()

        expect:
        multimediaService.count() == 5

        when:
        multimediaService.delete(multimediaId)
        sessionFactory.currentSession.flush()

        then:
        multimediaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Multimedia multimedia = new Multimedia()
        multimediaService.save(multimedia)

        then:
        multimedia.id != null
    }
}
