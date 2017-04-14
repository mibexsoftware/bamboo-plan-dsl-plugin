package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class NpmTaskSpec extends Specification {

    def 'NPM task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/NpmTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new NpmTask(
                enabled: true,
                isFinal: false,
                description: "execute NPM",
                environmentVariables: "what=EVER",
                command: "install",
                useIsolatedCache: true,
                workingSubDirectory: ".",
                executable: "/usr/bin/nodejs",
                pluginKey: 'com.atlassian.bamboo.plugins.bamboo-nodejs-plugin:task.builder.npm'
        )
    }

}
