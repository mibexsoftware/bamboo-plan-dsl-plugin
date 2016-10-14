package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class JobSpec extends Specification {

    def 'multiple jobs'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/jobs/MultipleJobs.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs.size() == 3
    }

}