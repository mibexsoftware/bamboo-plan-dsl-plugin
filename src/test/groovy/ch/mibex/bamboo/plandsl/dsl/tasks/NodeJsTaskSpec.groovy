package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class NodeJsTaskSpec extends Specification {

    def 'NodeJS task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/NodeJsTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new NodeJsTask(
                enabled: true,
                isFinal: false,
                description: "execute Node",
                environmentVariables: "what=EVER",
                arguments: "-n",
                workingSubDirectory: ".",
                executable: "/usr/bin/nodejs",
                script: "package.json",
                pluginKey: "com.atlassian.bamboo.plugins.bamboo-nodejs-plugin:task.builder.node"
        )
    }

}
