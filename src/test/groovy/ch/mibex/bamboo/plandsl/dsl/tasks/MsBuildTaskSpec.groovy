package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class MsBuildTaskSpec extends Specification {

    def 'MSBuild task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/MsBuildTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new MsBuildTask(
                enabled: true,
                isFinal: false,
                description: "run MSBuild",
                environmentVariables: "what=EVER",
                workingSubDirectory: ".",
                options: "/d",
                executable: "msbuild",
                pluginKey: "com.atlassian.bamboo.plugin.dotnet:msbuild",
                projectFile: "MySolution.sln"
        )
    }

}
