package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class AfterSuccessfulDeploymentTrigger extends DeploymentTriggerType {
    private String triggeringEnvironment

    /**
     * @deprecated use {@link #AfterSuccessfulDeploymentTrigger(String, BambooFacade)} instead
     */
    @Deprecated
    AfterSuccessfulDeploymentTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    AfterSuccessfulDeploymentTrigger(String triggeringEnvironment, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.triggeringEnvironment = Validations.isNotNullOrEmpty(triggeringEnvironment,
                'triggeringEnvironment must not be empty')
    }

    protected AfterSuccessfulDeploymentTrigger() {}

    /**
     * Triggering environment.
     *
     * @param triggeringEnvironment name of environment
     * @deprecated use {@link #AfterSuccessfulDeploymentTrigger(String, BambooFacade)} instead
     */
    @Deprecated
    void triggeringEnvironment(String triggeringEnvironment) {
        this.triggeringEnvironment = triggeringEnvironment
    }
}
