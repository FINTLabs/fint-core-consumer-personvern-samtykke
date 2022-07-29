package no.fintlabs

import no.fintlabs.consumer.Application
import spock.lang.Specification

class ApplicationSpec extends Specification {

    def "Application is created"() {
        when:
        def application = new Application()

        then:
        application
    }
}
