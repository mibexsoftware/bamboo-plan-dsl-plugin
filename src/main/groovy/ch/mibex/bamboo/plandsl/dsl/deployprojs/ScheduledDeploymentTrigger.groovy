package ch.mibex.bamboo.plandsl.dsl.deployprojs

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@ToString
@EqualsAndHashCode
class ScheduledDeploymentTrigger extends DeploymentTriggerType {
    String cronExpression
    String customPlanBranchName

    void customPlanBranchName(String customPlanBranchName) {
        this.customPlanBranchName = customPlanBranchName
    }

    void cronExpression(String cronExpression) {
        this.cronExpression = cronExpression
    }

}
