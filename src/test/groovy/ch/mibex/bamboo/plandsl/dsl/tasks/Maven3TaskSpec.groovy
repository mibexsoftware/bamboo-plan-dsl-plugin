package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class Maven3TaskSpec extends Specification {

    def 'inline script shell task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/Maven3Task.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new Maven3Task(
                enabled: true,
                isFinal: false,
                description: "build plug-in",
                goal: "install",
                environmentVariables: "what=EVER",
                workingSubDirectory: ".",
                buildJdk: "jdk8",
                executable: "maven323",
                hasTests: true,
                withTests: new WithTests(
                        testResultsDirectory: "tests/"
                ),
                useMavenReturnCode: false,
                projectFile: "a/b"
        )
    }

}
