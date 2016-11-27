package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ScheduledDeploymentTrigger extends DeploymentTriggerType {
    private String cronExpression
    private String customPlanBranchName

    ScheduledDeploymentTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
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
    void cronExpression(String cronExpression) {
        this.cronExpression = cronExpression
    }

}
