package radios.backend

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RadioServiceSpec extends Specification {

    RadioService radioService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Radio(...).save(flush: true, failOnError: true)
        //new Radio(...).save(flush: true, failOnError: true)
        //Radio radio = new Radio(...).save(flush: true, failOnError: true)
        //new Radio(...).save(flush: true, failOnError: true)
        //new Radio(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //radio.id
    }

    void "test get"() {
        setupData()

        expect:
        radioService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Radio> radioList = radioService.list(max: 2, offset: 2)

        then:
        radioList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        radioService.count() == 5
    }

    void "test delete"() {
        Long radioId = setupData()

        expect:
        radioService.count() == 5

        when:
        radioService.delete(radioId)
        sessionFactory.currentSession.flush()

        then:
        radioService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Radio radio = new Radio()
        radioService.save(radio)

        then:
        radio.id != null
    }
}
