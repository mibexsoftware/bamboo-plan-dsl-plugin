package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class DockerTaskSpec extends Specification {

    def 'Docker task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/DockerTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new DockerTask(
                enabled: true,
                isFinal: false,
                description: "build docker image",
                repository: "registry.address:port/namespace/repository:tag",
                command: DockerTask.DockerCommand.BUILD,
                environmentVariables: "what=EVER",
                dockerfileContents: "FROM debian:jessie-slim",
                workingSubDirectory: ".",
                saveTheImageAsFile: 'image.iso',
                doNotUseCachingWhenBuildingImage: true,
        )
    }

}
