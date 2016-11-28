package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ScheduledDeploymentTrigger extends DeploymentTriggerType {
    private String cronExpression
    private String customPlanBranchName

    @Deprecated
    ScheduledDeploymentTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    ScheduledDeploymentTrigger(String cronExpression, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.cronExpression = cronExpression
    }

    // just for testing
    protected ScheduledDeploymentTrigger() {}

    /**
     * Branch to provide artifacts for this deployment.
     *
     * @param customPlanBranchName plan branch name
     */
    void customPlanBranchName(String customPlanBranchName) {
        this.customPlanBranchName = customPlanBranchName
    }

    /**
     * Cron expression.
     *
     * @param cronExpression cron expression
     */
    @Deprecated
    void cronExpression(String cronExpression) {
        this.cronExpression = cronExpression
    }

}
