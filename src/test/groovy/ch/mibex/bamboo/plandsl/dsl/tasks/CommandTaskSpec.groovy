package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class CommandTaskSpec extends Specification {

    def 'command task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/CommandTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new CommandTask(
                enabled: true,
                isFinal: false,
                description: "run command",
                argument: "-n",
                executable: "atlas-clean",
                environmentVariables: "what=EVER",
                workingSubDirectory: ".",
        )
    }
}