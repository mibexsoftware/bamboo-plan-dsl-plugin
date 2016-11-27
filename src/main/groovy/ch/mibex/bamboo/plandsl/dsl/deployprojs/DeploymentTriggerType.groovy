package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class DeploymentTriggerType extends BambooObject {
    protected String displayName
    protected boolean enabled = true

    DeploymentTriggerType(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    protected DeploymentTriggerType() {}

    /**
     * The name of the trigger.
     *
     * @param displayName name of the trigger
     */
    void displayName(String displayName) {
        this.displayName = displayName
    }

    /**
     * If the trigger is enabled.
     */
    void enabled(boolean enabled = true) {
        this.enabled = enabled
    }
}
