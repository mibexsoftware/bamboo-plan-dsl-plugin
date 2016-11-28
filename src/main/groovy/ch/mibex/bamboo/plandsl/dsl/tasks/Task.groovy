package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
abstract class Task extends BambooObject {
    protected boolean enabled = true // we want tasks to be enabled by default
    protected boolean isFinal
    protected String description
    protected String pluginKey

    protected Task(BambooFacade bambooFacade, String pluginKey) {
        super(bambooFacade)
        this.pluginKey = Validations.isNotNullOrEmpty(pluginKey, 'pluginKey must not be empty')
    }

    protected Task(String pluginKey) {
        this.pluginKey = Validations.isNotNullOrEmpty(pluginKey, 'pluginKey must not be empty')
    }

    protected abstract def Map<String, String> getConfig(Map<Object, Object> context)

    /**
     * Enable or disable this task.
     */
    void enabled(boolean enabled = true) {
        this.enabled = enabled
    }

    /**
     * Final tasks are always executed even if a previous task fails.
     */
    void isFinal(boolean isFinal = true) {
        this.isFinal = isFinal
    }

    /**
     * Task description.
     */
    void description(String description) {
        this.description = description
    }
}
