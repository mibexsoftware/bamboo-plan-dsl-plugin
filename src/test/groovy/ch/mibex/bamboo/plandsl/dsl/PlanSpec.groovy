package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class PlanSpec extends Specification {

    def 'simple valid plan definition'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def testLogger = new NullLogger()
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/SimplePlan.groovy').text), testLogger)

        then:
        results != null
        results.projects.size() == 1
        results.projects[0].key == "SIMPLEPROJECT"
        results.projects[0].name == "Simple project"
        results.projects[0].plans[0] == new Plan(
                key: "SIMPLEPLAN",
                name: "Simple plan",
                enabled: true,
                description: "this is a simple plan"
        )
    }

    def 'plan with invalid plan key from DSL text'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def testLogger = new NullLogger()
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/InvalidPlanKey.txt').text), testLogger)

        then:
        Exception e = thrown(DslScriptException)
        e.message == '[DSL script, line 6]: plan key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
    }


    def 'plan with invalid plan key from DSL script'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def testLogger = new NullLogger()
        def resource = getClass().getResource('/dsls/plans/InvalidPlanKey.txt')
        loader.parse(new DslScriptContext(new File(resource.toURI()).absolutePath, null, null), testLogger)

        then:
        Exception e = thrown(DslScriptException)
        e.message == '[InvalidPlanKey.txt, line 6]: plan key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
    }

}