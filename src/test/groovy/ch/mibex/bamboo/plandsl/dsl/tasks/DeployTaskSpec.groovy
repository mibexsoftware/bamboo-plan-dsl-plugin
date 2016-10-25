package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class DeployTaskSpec extends Specification {

    def 'Deploy plug-in task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/DeployPluginTask.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new DeployPluginTask(
                enabled: true,
                isFinal: false,
                description: "Deploy plug-in to staging server",
                productType: DeployPluginTask.ProductType.STASH,
                deployUsername: 'admin',
                deployArtifactName: "Plan DSL",
                deployURL: 'http://myserver',
                deployPasswordVariable: '${bamboo.bitbucket_server_password}',
                deployBranchEnabled: true,
                certificateCheckDisabled: true
        )
    }
}