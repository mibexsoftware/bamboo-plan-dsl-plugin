package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.DslParentElement
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode
@ToString
class DeploymentTriggers implements DslParentElement<DeploymentTriggerType> {
    Set<DeploymentTriggerType> triggers = new LinkedHashSet<>()

    void scheduled(String displayName, @DelegatesTo(ScheduledDeploymentTrigger) Closure closure) {
        def scheduled = new ScheduledDeploymentTrigger()
        scheduled.displayName = displayName
        DslScriptHelper.execute(closure, scheduled)
        triggers << scheduled
    }

    void afterSuccessfulBuildPlan(String displayName,
                                  @DelegatesTo(AfterSuccessfulBuildDeploymentTrigger) Closure closure) {
        def trigger = new AfterSuccessfulBuildDeploymentTrigger()
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    void afterSuccessfulDeployment(String displayName,
                                   @DelegatesTo(AfterSuccessfulDeploymentTrigger) Closure closure) {
        def trigger = new AfterSuccessfulDeploymentTrigger()
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    void afterSuccessfulStage(String displayName,
                              @DelegatesTo(AfterSuccessfulStageDeploymentTrigger) Closure closure) {
        def trigger = new AfterSuccessfulStageDeploymentTrigger()
        trigger.displayName = displayName
        DslScriptHelper.execute(closure, trigger)
        triggers << trigger
    }

    @Override
    Collection<DeploymentTriggerType> children() {
        triggers
    }

}
