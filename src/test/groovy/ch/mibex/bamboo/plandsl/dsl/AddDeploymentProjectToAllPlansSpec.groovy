package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class AddDeploymentProjectToAllPlansSpec extends Specification {

    def 'simple valid plan definition'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def testLogger = new NullLogger()
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/AddDeploymentProjectToAllPlans.groovy').text), testLogger)

        then:
        results != null
    }
}