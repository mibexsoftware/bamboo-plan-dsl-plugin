package ch.mibex.bamboo.plandsl.dsl.deployprojs

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode
@ToString
class DeploymentTriggerType {
    String displayName
    boolean enabled = true

    void displayName(String displayName) {
        this.displayName = displayName
    }

    void enabled(boolean enabled = true) {
        this.enabled = enabled
    }
}
