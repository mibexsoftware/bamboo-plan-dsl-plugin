package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class CommonTasksSpec extends Specification {

    def 'common tasks reuse'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/CommonTasks.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].deploymentProjects[0].environments[2].tasks.tasks == [
                new CleanWorkingDirTask(
                ),
                new ScriptTask(
                    enabled: true,
                    isFinal: false,
                    description: "Update scripts",
                    inlineScript: new InlineScript(
                            scriptBody: 'cd /home/bamboo/scripts && git pull',
                            interpreter: ScriptTask.ScriptInterpreter.LEGACY_SH_BAT
                    )
                ),
                new ScriptTask(
                    enabled: true,
                    isFinal: false,
                    description: "Prepare deployment",
                    scriptFile: new ScriptFile(
                            scriptFile: '/home/bamboo/scripts/build/<script>'
                    )
                )
        ]
    }
}