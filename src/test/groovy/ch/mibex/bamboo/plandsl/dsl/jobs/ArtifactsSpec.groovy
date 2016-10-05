package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.NullLogger
import spock.lang.Specification

class ArtifactsSpec extends Specification {

    def 'artifact definitions'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/jobs/ArtifactDefinitions.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl), new NullLogger())
        def artifactDefinitions = results.projects[0].plans[0].stages[0].jobs[0].artifacts.artifactDefinitions

        then:
        artifactDefinitions[0] == new ArtifactDefinition(
            name: "my JAR",
            location: "target",
            copyPattern: "**/*.jar",
            isShared: true
        )
        artifactDefinitions[1] == new ArtifactDefinition(
                name: "my ZIP",
                location: "target",
                copyPattern: "**/*.zip",
                isShared: true
        )
    }

    def 'artifact dependency'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/jobs/ArtifactDependency.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl), new NullLogger())
        def plan = results.projects[0].plans[0]

        then:
        plan.stages[0].jobs[0].artifacts.artifactDefinitions[0] == new ArtifactDefinition(
                name: "my JAR",
                location: "target",
                copyPattern: "**/*.jar",
                isShared: true
        )
        plan.stages[1].jobs[0].artifacts.artifactDependencies[0] == new ArtifactDependency(
                name: "my JAR",
                destinationDirectory: "target"
        )
    }

}
