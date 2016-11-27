package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class AfterSuccessfulDeploymentTrigger extends DeploymentTriggerType {
    private String triggeringEnvironment

    AfterSuccessfulDeploymentTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    protected AfterSuccessfulDeploymentTrigger() {}

    /**
     * Triggering environment.
     *
     * @param triggeringEnvironment name of environment
     */
    void triggeringEnvironment(String triggeringEnvironment) {
        this.triggeringEnvironment = triggeringEnvironment
    }

}
