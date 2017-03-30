package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class EnvironmentSpec extends Specification {

    def 'environments'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/deployprojs/Environments.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].deploymentProjects[0].environments[0] == new Environment(id: 59998211, name: "env1")
        results.projects[0].plans[0].deploymentProjects[0].environments[1] == new Environment(id: 59998212, name: "env2")
        //FIXME: contents are identical!
//        results.projects[0].plans[0].deploymentProjects[0].environments[2] == new Environment(
//                name: "env",
//                id: null,
//                description: "env desc",
//                tasks: new Tasks(tasks: [new CommandTask(
//                        enabled: true,
//                        isFinal: true,
//                        workingSubDirectory: ".",
//                        argument: "-n",
//                        environmentVariables: "what=EVER",
//                        executable: "atlas-clean"
//                )]),
//                triggers: new DeploymentTriggers(triggers: [new ScheduledDeploymentTrigger(
//                        cronExpression: "0 0 0 ? * *"
//                ), new AfterSuccessfulStageDeploymentTrigger(
//                        customPlanBranchName: "develop",
//                        planStageToTriggerThisDeployment: "another stage"
//                ), new AfterSuccessfulBuildDeploymentTrigger(
//                        customPlanBranchName: "develop"
//                )]),
//                variables: new Variables(variables: [new Variable("key1", "value1"), new Variable("key2", "value2")])
//        )
    }

}