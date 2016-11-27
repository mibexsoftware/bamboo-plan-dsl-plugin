package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class AfterSuccessfulBuildDeploymentTrigger extends DeploymentTriggerType {
    private String customPlanBranchName

    AfterSuccessfulBuildDeploymentTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    protected AfterSuccessfulBuildDeploymentTrigger() {}

    /**
     * Branch to trigger this deployment.
     *
     * @param customPlanBranchName name of plan branch
     */
    void customPlanBranchName(String customPlanBranchName) {
        this.customPlanBranchName = customPlanBranchName
    }

}
