package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptException
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

    def 'job without name'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/jobs/InvalidJobWithoutName.groovy').text

        when:
        loader.parse(new DslScriptContext(dsl))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:16): job name must be specified'
    }

    def 'job in external script'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/jobs/JobInExternalScript.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        def jobs = results.projects[0].plans[0].stages[0].jobs
        jobs.size() == 2
        jobs[0].key == 'SIMPLEJOB2'
        jobs[0].name ==  'Build plug-in'
        jobs[0].description == 'analyzes the code, runs tests and builds the plug-in'
        jobs[0].children().size() == 3
        jobs[1].artifacts.artifactDefinitions.size() == 1
        jobs[1].key == 'SIMPLEJOB1'
        jobs[1].children().size() == 3
        jobs[1].artifacts.artifactDefinitions.size() == 1
    }

}