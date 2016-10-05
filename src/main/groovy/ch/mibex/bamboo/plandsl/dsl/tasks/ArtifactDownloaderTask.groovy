package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString

class ArtifactDownloaderTask extends Task {
    static final TASK_ID = 'com.atlassian.bamboo.plugins.bamboo-artifact-downloader-plugin:artifactdownloadertask'
    Set<ArtifactDownloadConfiguration> artifacts = new LinkedHashSet<>()

    ArtifactDownloaderTask() {
        super(TASK_ID)
    }

    // multiple artifacts possible!
    void artifact(String name, @DelegatesTo(ArtifactDownloadConfiguration) Closure closure) {
        def config = new ArtifactDownloadConfiguration(name)
        DslScriptHelper.execute(closure, config)
        artifacts << config
    }

    void allArtifacts(@DelegatesTo(ArtifactDownloadConfiguration) Closure closure) {
        def config = new ArtifactDownloadConfiguration(null)
        DslScriptHelper.execute(closure, config)
        artifacts << config
    }

    @Override
    Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        def contextArtifacts = context['artifacts']
        artifacts.eachWithIndex { dslArtifact, idx ->
            def artifact = contextArtifacts[dslArtifact.name]
            if (artifact) {
                config.put('artifactId_' + idx, artifact.asType(ArtifactInfo).artifactId.toString())
            }
            config.put('localPath_' + idx, dslArtifact.destinationPath)
        }
        config.put('sourcePlanKey', context['planKey'])
        config
    }
}
