package ch.mibex.bamboo.plandsl.dsl.deployprojs

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@ToString
@EqualsAndHashCode
class AfterSuccessfulBuildDeploymentTrigger extends DeploymentTriggerType {
    String customPlanBranchName

    void customPlanBranchName(String customPlanBranchName) {
        this.customPlanBranchName = customPlanBranchName
    }

}
