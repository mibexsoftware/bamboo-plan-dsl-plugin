package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class ShipItPluginTask extends Task {
    String deployArtifactName
    boolean onlyAllowToTriggerFromJira
    boolean runOnBranchBuilds
    String jiraProjectKey
    boolean publicVersion
    boolean deduceBuildNrFromPluginVersion
    String bambooUserId
    String jqlToCollectReleaseNotes

    static final TASK_ID = 'ch.mibex.bamboo.shipit2mpac:shipit2marketplace.task'

    ShipItPluginTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    //for tests
    protected ShipItPluginTask() {}

    void deployArtifactName(String deployArtifactName) {
        this.deployArtifactName = deployArtifactName
    }

    void onlyAllowToTriggerFromJira(boolean onlyAllowToTriggerFromJira) {
        this.onlyAllowToTriggerFromJira = onlyAllowToTriggerFromJira
    }

    void jiraProjectKey(String jiraProjectKey) {
        this.jiraProjectKey = jiraProjectKey
    }

    void runOnBranchBuilds(boolean runOnBranchBuilds) {
        this.runOnBranchBuilds = runOnBranchBuilds
    }

    void publicVersion(boolean publicVersion) {
        this.publicVersion = publicVersion
    }

    void deduceBuildNrFromPluginVersion(boolean deduceBuildNrFromPluginVersion) {
        this.deduceBuildNrFromPluginVersion = deduceBuildNrFromPluginVersion
    }

    void bambooUserId(String bambooUserId) {
        this.bambooUserId = bambooUserId
    }

    void jqlToCollectReleaseNotes(String jqlToCollectReleaseNotes) {
        this.jqlToCollectReleaseNotes = jqlToCollectReleaseNotes
    }

    @Override
    Map<String, String> getConfig(Map<Object, Object> context) {
        Map<String, String> config = [:]
        config.put('jiraReleasePanelDeploymentOnly', onlyAllowToTriggerFromJira  as String)
        config.put('runOnBranchBuilds', runOnBranchBuilds as String)
        config.put('jiraProjectKey', jiraProjectKey)
        config.put('publicVersion', publicVersion as String)
        config.put('deduceBuildNrFromPluginVersion', deduceBuildNrFromPluginVersion as String)
        config.put('bambooUserId', bambooUserId)
        config.put('jql', jqlToCollectReleaseNotes)
        def contextArtifacts = context['artifacts']
        def artifact = contextArtifacts[deployArtifactName]
        if (artifact) {
            def info = artifact.asType(ArtifactInfo)
            def artifactKey = "v2:${info.artifactId}:${info.taskId}:${info.transferId}:${info.name}".toString()
            config.put('artifactToDeployKey', artifactKey)
        }
        config
    }

}
