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
//                permissions: new Permissions(
//                        user: ['diego': [PermissionTypes.PermissionType.VIEW]],
//                        group: ['devops': [PermissionTypes.PermissionType.VIEW]],
//                        other: [(Permissions.OtherUserType.LOGGED_IN_USERS): [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.EDIT]],
//                ),
//                triggers: new DeploymentTriggers(
//                        triggers: [
//                                new ScheduledTrigger(
//                                    cronExpression: "0 0 0 ? * *"
//                                ),
//                                new AfterSuccessfulStageDeploymentTrigger(
//                                        customPlanBranchName: "develop",
//                                        planStageToTriggerThisDeployment: "another stage"
//                                ),
//                                new AfterSuccessfulBuildDeploymentTrigger(
//                                        customPlanBranchName: "develop"
//                                ),
//                                new AfterSuccessfulDeploymentTrigger(
//                                        triggeringEnvironment: "PROD"
//                                )
//                        ]
//                ),
//                notifications: new EnvironmentNotifications(
//                        notifications: [
//                                new EmailNotification(
//                                        event: Notifications.NotificationEvent.DEPLOYMENT_FAILED,
//                                        address: 'your@mail.com'
//                                ),
//                                new UserNotification(
//                                        event: Notifications.NotificationEvent.DEPLOYMENT_FAILED,
//                                        user: 'bob'
//                                ),
//                                new CustomNotification(
//                                        notificationRecipientType: 'ch.mibex.bamboo.smsnotification:smsnotification.recipient',
//                                        event: Notifications.NotificationEvent.FAILED_BUILDS_AND_FIRST_SUCCESSFUL,
//                                        numberOfFailures: 1,
//                                        config: [
//                                                'twilioAccountSid': ['twilioAccountSid'],
//                                                'twilioAuthToken': ['twilioAuthToken'],
//                                                'smsFromNumber': ['smsFromNumber'],
//                                                'smsToNumber': ['smsToNumber']
//                                        ]
//                                )
//                        ]
//                ),
//                agentsAssignment: new AgentsAssignment(
//                        requirements: new Requirements(
//                            requirements: [
//                                    new Requirement(
//                                        capabilityKey: 'system.builder.gradle.Gradle 2.2',
//                                        matchType: new Requirement.Equals(matchValue: '2.2')
//                                    )
//                            ]
//                        ),
//                        dedicatedAgentNames: ['Default Agent', 'localhost']
//                ),
//                variables: new Variables(variables: [new Variable("key1", "value1"), new Variable("key2", "value2")])
//        )
    }

}