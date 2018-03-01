package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.NullBambooFacade
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
        this.pluginKey = pluginKey
    }

    protected Task(String pluginKey) {
        this(new NullBambooFacade(), pluginKey)
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

    protected String getArtifactId(Map<Object, Object> context, String deployArtifactName, boolean v2 = false) {
        def contextArtifacts = context['artifacts']
        def artifact = contextArtifacts[deployArtifactName]
        Validations.requireNotNullOrEmpty(
                artifact, "artifact details for '$deployArtifactName' not found in task $pluginKey: " + context
        )
        def info = artifact.asType(ArtifactInfo)
        def prefix = v2 ? 'v2:' : ''
        "${prefix}${info.artifactId}:${info.taskId}:${info.transferId}:${info.name}".toString()
    }
}
