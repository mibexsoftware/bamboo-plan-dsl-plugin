package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ArtifactDownloaderTask extends Task {
    private static final TASK_ID =
            'com.atlassian.bamboo.plugins.bamboo-artifact-downloader-plugin:artifactdownloadertask'
    private List<ArtifactDownloadConfiguration> artifacts = []

    ArtifactDownloaderTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    /**
     * You can choose multiple artifacts by name here. Just call this method multiple times with different names.
     */
    void artifact(String name, @DelegatesTo(ArtifactDownloadConfiguration) Closure closure) {
        def config = new ArtifactDownloadConfiguration(name, bambooFacade)
        DslScriptHelper.execute(closure, config)
        artifacts << config
    }

    /**
     * You can choose multiple artifacts by name here. Just call this method multiple times with different names.
     *
     * @param params the properties for the artifact. Currently, only "name" is expected.
     */
    void artifact(Map<String, String> params, @DelegatesTo(ArtifactDownloadConfiguration) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        artifact(params['name'], closure)
    }

    /**
     * All artifacts get downloaded.
     */
    void allArtifacts(@DelegatesTo(ArtifactDownloadConfiguration) Closure closure) {
        artifact(null as String, closure)
    }

    @Override
    protected Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        def contextArtifacts = context['artifacts']
        artifacts.eachWithIndex { dslArtifact, idx ->
            def artifact = contextArtifacts[dslArtifact.name]
            // if all artifacts are chosen, just use -1 as the artifact ID
            config.put('artifactId_' + idx, String.valueOf(artifact ? artifact.asType(ArtifactInfo).artifactId : -1))
            config.put('localPath_' + idx, dslArtifact.destinationPath)
            config.put('sourcePlanKey', dslArtifact.sourcePlanKey ?: context['planKey'])
        }
        config
    }
}
