package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class ScriptTaskSpec extends Specification {

    def 'inline script shell task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/InlineShellTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))
        def scriptTask = results.projects[0].plans[0].stages[0].jobs[0].tasks.tasks[0]

        then:
        scriptTask == new ScriptTask(
                enabled: true,
                isFinal: false,
                description: "say hello world",
                argument: "-n",
                environmentVariables: "what=EVER",
                workingSubDirectory: ".",
                inlineScript: new InlineScript(
                        scriptBody: 'echo "Hello world"',
                        interpreter: ScriptTask.ScriptInterpreter.LEGACY_SH_BAT
                )
        )
    }

    def 'file shell task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/FileScriptShellTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))
        def scriptTask = results.projects[0].plans[0].stages[0].jobs[0].tasks.tasks[0]

        then:
        scriptTask == new ScriptTask(
                enabled: true,
                isFinal: true,
                description: "run file script",
                argument: "-n",
                environmentVariables: "what=EVER",
                workingSubDirectory: ".",
                scriptFile: new ScriptFile(
                        scriptFile: 'bin/test.sh',
                        interpreter: ScriptTask.ScriptInterpreter.RUN_AS_EXECUTABLE
                )
        )
    }
}