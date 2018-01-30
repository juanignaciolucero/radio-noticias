package radios.backend

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AdServiceSpec extends Specification {

    AdService adService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Ad(...).save(flush: true, failOnError: true)
        //new Ad(...).save(flush: true, failOnError: true)
        //Ad ad = new Ad(...).save(flush: true, failOnError: true)
        //new Ad(...).save(flush: true, failOnError: true)
        //new Ad(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //ad.id
    }

    void "test get"() {
        setupData()

        expect:
        adService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Ad> adList = adService.list(max: 2, offset: 2)

        then:
        adList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        adService.count() == 5
    }

    void "test delete"() {
        Long adId = setupData()

        expect:
        adService.count() == 5

        when:
        adService.delete(adId)
        sessionFactory.currentSession.flush()

        then:
        adService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Ad ad = new Ad()
        adService.save(ad)

        then:
        ad.id != null
    }
}
