package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class ShipItTaskSpec extends Specification {

    def 'shipit task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/ShipItTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasks.tasks[0] == new ShipItPluginTask(
                enabled: true,
                isFinal: false,
                description: "shipit to the Atlassian Marketplace",
                deployArtifactName: "Plan DSL",
                onlyAllowToTriggerFromJira: true,
                runOnBranchBuilds: false,
                publicVersion: true,
                deduceBuildNrFromPluginVersion: true,
                bambooUserId: 'admin',
                jqlToCollectReleaseNotes: 'status in (resolved,closed,done)'
        )
    }
}