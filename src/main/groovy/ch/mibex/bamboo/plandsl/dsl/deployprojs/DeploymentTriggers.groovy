package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class DeploymentTriggers extends BambooObject {
    private List<DeploymentTriggerType> triggers = []

    // just for testing
    protected DeploymentTriggers() {}

    DeploymentTriggers(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Run according to schedule.
     *
     * @param displayName The name of the trigger
     */
    void scheduled(String displayName, @DelegatesTo(ScheduledDeploymentTrigger) Closure closure) {
        def scheduled = new ScheduledDeploymentTrigger(bambooFacade)
        scheduled.displayName = displayName
        DslScriptHelper.execute(closure, scheduled)
        triggers << scheduled
    }

    /**
     * Deployment is started after a plan is successfully built.
     *
     * @param displayName The name of the trigger
     */
    void afterSuccessfulBuildPlan(String displayName,
                                  @DelegatesTo(AfterSuccessfulBuildDeploymentTrigger) Closure closure) {
        def trigger = new AfterSuccessfulBuildDeploymentTrigger(bambooFacade)
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Deployment is started after a deployment on another environment is completed successfully.
     *
     * @param displayName The name of the trigger
     */
    void afterSuccessfulDeployment(String displayName,
                                   @DelegatesTo(AfterSuccessfulDeploymentTrigger) Closure closure) {
        def trigger = new AfterSuccessfulDeploymentTrigger(bambooFacade)
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    /**
     * Deployment is started after a stage is successfully built.
     *
     * @param displayName The name of the trigger
     */
    void afterSuccessfulStage(String displayName,
                              @DelegatesTo(AfterSuccessfulStageDeploymentTrigger) Closure closure) {
        def trigger = new AfterSuccessfulStageDeploymentTrigger(bambooFacade)
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

}
