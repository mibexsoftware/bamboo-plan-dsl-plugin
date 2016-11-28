package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class AfterSuccessfulStageDeploymentTrigger extends DeploymentTriggerType {
    private String customPlanBranchName
    private String planStageToTriggerThisDeployment

    AfterSuccessfulStageDeploymentTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    protected AfterSuccessfulStageDeploymentTrigger() {}

    /**
     * Branch to trigger this deployment.
     *
     * @param customPlanBranchName name of branch plan
     */
    void customPlanBranchName(String customPlanBranchName) {
        this.customPlanBranchName = customPlanBranchName
    }

    /**
     * Plan stage to trigger this deployment build plug-in.
     *
     * @param planStageToTriggerThisDeployment name of plan stage
     */
    void planStageToTriggerThisDeployment(String planStageToTriggerThisDeployment) {
        this.planStageToTriggerThisDeployment = planStageToTriggerThisDeployment
    }
}
