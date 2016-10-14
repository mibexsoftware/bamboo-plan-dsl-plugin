package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.AbstractBambooElement
import ch.mibex.bamboo.plandsl.dsl.BambooFacade

abstract class Task extends AbstractBambooElement {
    boolean enabled = true // we want tasks to be enabled by default
    boolean isFinal
    String description
    String pluginKey

    // for tests
    protected Task() {}

    protected Task(BambooFacade bambooFacade, String pluginKey) {
        super(bambooFacade)
        this.pluginKey = pluginKey
    }

    abstract def Map<String, String> getConfig(Map<Object, Object> context)

    void enabled(boolean enabled) {
        this.enabled = enabled
    }

    void isFinal(boolean isFinal) {
        this.isFinal = isFinal
    }

    void description(String description) {
        this.description = description
    }
}
