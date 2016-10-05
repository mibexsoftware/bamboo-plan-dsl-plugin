package ch.mibex.bamboo.plandsl.dsl.tasks

abstract class Task {
    boolean enabled = true // we want tasks to be enabled by default
    boolean isFinal
    String description
    final String pluginKey

    protected Task(String pluginKey) {
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
