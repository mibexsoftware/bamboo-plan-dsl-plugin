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
     * @param description The description of the trigger
     * @deprecated use {@link #scheduledCron(String, Closure)} instead
     */
    @Deprecated
    void scheduled(String description,
                   @DelegatesTo(value = ScheduledDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure c) {
        def scheduled = new ScheduledDeploymentTrigger(bambooFacade)
        scheduled.description = description
        DslScriptHelper.execute(c, scheduled)
        triggers << scheduled
    }

    /**
     * Run according to schedule.
     *
     * @param cronExpression cron expression
     */
    void scheduledCron(
            String cronExpression,
            @DelegatesTo(value = ScheduledDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def scheduled = new ScheduledDeploymentTrigger(cronExpression, bambooFacade)
        DslScriptHelper.execute(closure, scheduled)
        triggers << scheduled
    }

    /**
     * Run according to schedule.
     *
     * @param cronExpression cron expression
     */
    void scheduledCron(
            Map<String, String> params,
            @DelegatesTo(value = ScheduledDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        scheduledCron(params['cronExpression'], closure)
    }

    /**
     * Deployment is started after a plan is successfully built.
     *
     * @param description The description of the trigger
     * @deprecated use {@link #afterSuccessfulBuildPlan(Closure)} instead
     */
    @Deprecated
    void afterSuccessfulBuildPlan(
            String description,
            @DelegatesTo(value = AfterSuccessfulBuildDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure c) {
        def trigger = new AfterSuccessfulBuildDeploymentTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(c, trigger)
        triggers << trigger
    }

    /**
     * Deployment is started after a plan is successfully built.
     */
    void afterSuccessfulBuildPlan(
            @DelegatesTo(value = AfterSuccessfulBuildDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure c) {
        afterSuccessfulBuildPlan(null, c)
    }

    /**
     * Deployment is started after a deployment on another environment is completed successfully.
     *
     * @param description The description of the trigger
     * @deprecated use {@link #afterSuccessfulDeployment(String, Closure)} instead
     */
    @Deprecated
    void afterSuccessfulDeployment(
            String description,
            @DelegatesTo(value = AfterSuccessfulBuildDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure c) {
        def trigger = new AfterSuccessfulDeploymentTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(c, trigger)
        triggers << trigger
    }

    /**
     * Deployment is started after a deployment on another environment is completed successfully.
     *
     * @param triggeringEnvironment name of environment
     */
    void afterSuccessDeployment(
            String triggeringEnvironment,
            @DelegatesTo(value = AfterSuccessfulBuildDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure c) {
        def trigger = new AfterSuccessfulDeploymentTrigger(triggeringEnvironment, bambooFacade)
        DslScriptHelper.execute(c, trigger)
        triggers << trigger
    }

    /**
     * Deployment is started after a deployment on another environment is completed successfully.
     *
     * @param triggeringEnvironment name of environment
     */
    void afterSuccessDeployment(
            Map<String, String> params,
            @DelegatesTo(value = AfterSuccessfulBuildDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure c) {
        afterSuccessDeployment(params['triggeringEnvironment'], c)
    }

    /**
     * Deployment is started after a stage is successfully built.
     *
     * @param description The description of the trigger
     * @deprecated use {@link #afterSuccessfulStage(Closure)} instead
     */
    @Deprecated
    void afterSuccessfulStage(
            String description,
            @DelegatesTo(value = AfterSuccessfulBuildDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure c) {
        def trigger = new AfterSuccessfulStageDeploymentTrigger(bambooFacade)
        trigger.description = description
        DslScriptHelper.execute(c, trigger)
        triggers << trigger
    }

    /**
     * Deployment is started after a stage is successfully built.
     */
    void afterSuccessfulStage(
            @DelegatesTo(value = AfterSuccessfulBuildDeploymentTrigger, strategy = Closure.DELEGATE_FIRST) Closure c) {
        afterSuccessfulStage(null, c)
    }
}
