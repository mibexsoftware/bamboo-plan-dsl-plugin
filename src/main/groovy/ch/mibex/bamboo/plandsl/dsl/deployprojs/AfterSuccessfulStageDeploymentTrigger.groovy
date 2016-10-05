package ch.mibex.bamboo.plandsl.dsl.deployprojs

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@ToString
@EqualsAndHashCode
class AfterSuccessfulStageDeploymentTrigger extends DeploymentTriggerType {
    String customPlanBranchName
    String planStageToTriggerThisDeployment

    void customPlanBranchName(String customPlanBranchName) {
        this.customPlanBranchName = customPlanBranchName
    }

    void planStageToTriggerThisDeployment(String planStageToTriggerThisDeployment) {
        this.planStageToTriggerThisDeployment = planStageToTriggerThisDeployment
    }
}
