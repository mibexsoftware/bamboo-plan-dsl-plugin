package ch.mibex.bamboo.plandsl.dsl.deployprojs

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@ToString
@EqualsAndHashCode
class AfterSuccessfulDeploymentTrigger extends DeploymentTriggerType {
    String triggeringEnvironment

    void triggeringEnvironment(String triggeringEnvironment) {
        this.triggeringEnvironment = triggeringEnvironment
    }

}
