package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.NullLogger
import spock.lang.Specification

class InectBambooVariablesSpec extends Specification {

    def 'command task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/InjectBambooVariablesTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl), new NullLogger())

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new InjectBambooVariablesTask(
                enabled: true,
                isFinal: false,
                description: "run command",
                propertiesFilePath: "env.txt",
                variablesScope: InjectBambooVariablesTask.VariablesScope.LOCAL,
                namespace: "test",
        )
    }
}