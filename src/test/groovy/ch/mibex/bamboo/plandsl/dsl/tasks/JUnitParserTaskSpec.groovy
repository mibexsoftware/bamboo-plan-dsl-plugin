package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class JUnitParserTaskSpec extends Specification {

    def 'Junit Parser task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/JUnitParserTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new JUnitParserTask(
                enabled: true,
                isFinal: false,
                description: 'parse test results',
                testResultsDirectory: 'test-reports/*.xml',
                pickUpTestResultsCreatedOutsideOfBuild: true
        )
    }

}
