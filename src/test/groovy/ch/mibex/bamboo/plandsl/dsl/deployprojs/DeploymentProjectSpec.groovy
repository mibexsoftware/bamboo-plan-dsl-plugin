package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.tasks.CommandTask
import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks
import ch.mibex.bamboo.plandsl.dsl.variables.Variable
import ch.mibex.bamboo.plandsl.dsl.variables.Variables
import spock.lang.Ignore
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
                )],
                releaseVersioning: new ReleaseVersioning(
                        nextReleaseVersion: "1.0-m1",
                        autoIncrement: true,
                        variables: ["test1", "test2"]
                )
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
                        description: "desc2",
                        variables: new Variables(variables: [new Variable("key1", "value1"), new Variable("key2", "value2")])
                )],
                releaseVersioning: new ReleaseVersioning(
                        nextReleaseVersion: "1.0-\${bamboo.buildNumber}",
                        autoIncrement: false,
                        variables: ["test3", "test4"]
                )
        )
    }

    @Ignore
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
                    tasks: new Tasks(tasks: [new CommandTask(
                            enabled: true,
                            isFinal: true,
                            workingSubDirectory: ".",
                            argument: "-n",
                            environmentVariables: "what=EVER",
                            executable: "atlas-clean"
                    )]),
                    triggers: new DeploymentTriggers(triggers: [new ScheduledDeploymentTrigger(
                            cronExpression: "0 0 0 ? * *"
                    )])
            )],

        )
    }

    def 'deployment projects in external script'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/deployprojs/DeploymentProjectsInExternalScript.groovy').text))

        then:
        def deploymentProjects = results.projects[0].plans[0].deploymentProjects
        deploymentProjects.size() == 2
        deploymentProjects[0].name == 'local project'
        deploymentProjects[0].environments.size() == 2
        deploymentProjects[1].name == 'global project'
        deploymentProjects[1].environments.size() == 2
    }

}