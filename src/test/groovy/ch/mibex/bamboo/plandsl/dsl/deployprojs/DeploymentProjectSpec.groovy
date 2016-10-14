package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.tasks.CommandTask
import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks
import spock.lang.Specification

class DeploymentProjectSpec extends Specification {

    def 'deployment project settings'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/deployprojs/DeploymentProject.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].deploymentProjects[0] == new DeploymentProject(
                name: "dp1",
                description: "desc1",
                useCustomPlanBranch: "develop",
                environments: [new Environment(
                        name: "env1",
                        description: "desc"
                )]
        )
        results.projects[0].plans[0].deploymentProjects[1] == new DeploymentProject(
                name: "dp2",
                description: "desc1",
                useCustomPlanBranch: "release",
                environments: [new Environment(
                        name: "env1",
                        description: "desc1"
                ), new Environment(
                        name: "env2",
                        description: "desc2"
                )]
        )
    }

    def 'environments'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/deployprojs/Environments.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].deploymentProjects[0] == new DeploymentProject(
            name: "name",
            description: "desc",
            useCustomPlanBranch: "develop",
            environments: [new Environment(
                    name: "env",
                    description: "env desc",
                    tasks: new Tasks(tasks: [new CommandTask([
                            enabled: true,
                            isFinal: true,
                            workingSubDirectory: ".",
                            argument: "-n",
                            environmentVariables: "what=EVER",
                            executable: "atlas-clean",
                    ])]),
                    triggers: new DeploymentTriggers(triggers: [new ScheduledDeploymentTrigger(
                            cronExpression: "0 0 0 ? * *"
                    )])
            )],

        )
    }

}