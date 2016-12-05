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
        jobs.size() == 3
        jobs[0].key == 'SIMPLEJOB2'
        jobs[0].name ==  'Build plug-in'
        jobs[0].description == 'analyzes the code, runs tests and builds the plug-in'
        jobs[0].tasksList.tasks.size() == 3
        jobs[1].key == 'SIMPLEJOB3'
        jobs[1].name ==  'Build plug-in'
        jobs[1].description == 'analyzes the code, runs tests and builds the plug-in'
        jobs[1].tasksList.tasks.size() == 3
        jobs[2].artifacts.artifactDefinitions.size() == 1
        jobs[2].key == 'SIMPLEJOB1'
        jobs[2].tasksList.tasks.size() == 3
        jobs[2].artifacts.artifactDefinitions.size() == 1
    }

    def 'job without misc options'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/jobs/JobWithMiscOptions.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        def jobs = results.projects[0].plans[0].stages[0].jobs
        jobs.size() == 1
        jobs[0].key == 'SIMPLEJOB'
        jobs[0].name ==  'simple job'
        jobs[0].miscellaneous == new Miscellaneous(
                cleanWorkingDirectoryAfterEachBuild: true,
                buildHungOptions: new BuildHungOptions(
                        buildTimeMultiplier: 2.5,
                        logQuietTime: 10,
                        buildQueueTimeout: 60
                ),
                nCoverOutput: new NCoverOutput(
                        nCoverXmlDirectory: 'a/b/c'
                ),
                patternMatchLabelling: new PatternMatchLabelling(
                        regexPattern: '[a-z]+',
                        labels: 'test'
                ),
                cloverCodeCoverage: new CloverCodeCoverage(
                        cloverLicense: 'LICENSE',
                        cloverOptions: new CloverOptions(
                                generateCloverHistoricalReport: true,
                                generateJSONReport: true
                        ),
                        integrationOptions: CloverCodeCoverage.IntegrationOptions.AUTOMATICALLY_INTEGRATE_CLOVER_INTO_BUILD
                )
        )
    }
}