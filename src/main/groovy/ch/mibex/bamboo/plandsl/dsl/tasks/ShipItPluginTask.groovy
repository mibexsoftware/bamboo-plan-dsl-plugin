package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ShipItPluginTask extends Task {
    private String deployArtifactName
    private boolean onlyAllowToTriggerFromJira
    private boolean runOnBranchBuilds
    private String jiraProjectKey
    private boolean publicVersion
    private boolean deduceBuildNrFromPluginVersion
    private String bambooUserId
    private String jqlToCollectReleaseNotes
    private static final TASK_ID = 'ch.mibex.bamboo.shipit2mpac:shipit2marketplace.task'

    @Deprecated
    ShipItPluginTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    @Deprecated
    ShipItPluginTask(String deployArtifactName, BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
        this.deployArtifactName = deployArtifactName
    }

    //for tests
    protected ShipItPluginTask() {
        super(TASK_ID)
    }

    /**
     * This is the artifact to publish to the Atlassian Marketplace.
     */
    @Deprecated
    void deployArtifactName(String deployArtifactName) {
        this.deployArtifactName = deployArtifactName
    }

    /**
     * Only allow to trigger Marketplace deployments from the JIRA release panel. If activated, the task will only run
     * if the build is triggered from the JIRA release panel. If you also want to ship your plug-in versions when the
     * build is triggered manually or by pushing commits, uncheck this option and provide the JIRA project associated
     * with this build below. The version number will then be read from the artifact and has to match an existing JIRA
     * project version number. Furthermore, you will also have to provide a Bamboo user below.
     */
    void onlyAllowToTriggerFromJira(boolean onlyAllowToTriggerFromJira = true) {
        this.onlyAllowToTriggerFromJira = onlyAllowToTriggerFromJira
    }

    void jiraProjectKey(String jiraProjectKey) {
        this.jiraProjectKey = jiraProjectKey
    }

    /**
     * Allow this task to execute on branch builds. This is disabled by default, since you generally only want a
     * single branch of development being deployed to a single environment.
     */
    void runOnBranchBuilds(boolean runOnBranchBuilds = true) {
        this.runOnBranchBuilds = runOnBranchBuilds
    }

    /**
     * If disabled, a private version will be created in the Marketplace (not visible to customers).
     */
    void publicVersion(boolean publicVersion = true) {
        this.publicVersion = publicVersion
    }

    /**
     * This will deduce the build number for the Atlassian Marketplace from the plug-in version (e.g., plug-in version
     * 1.0.0 will result in the build number 100000000). You can override this by configuring the Bamboo build variable
     * "shipit2mpac.buildnr" in the JIRA release or the Bamboo build dialog.
     */
    void deduceBuildNrFromPluginVersion(boolean deduceBuildNrFromPluginVersion = true) {
        this.deduceBuildNrFromPluginVersion = deduceBuildNrFromPluginVersion
    }

    /**
     * If there is no Bamboo user with the same name as the one you use to trigger a release from JIRA or if you dont
     * want to use the JIRA release panel, provide a Bamboo user with JIRA access here. This user is used to fetch the
     * necessary data from JIRA to build the release notes.
     */
    void bambooUserId(String bambooUserId) {
        this.bambooUserId = bambooUserId
    }

    /**
     * The JQL to collect the JIRA issues associated with the plug-in version for creating release notes. You do not
     * have to specify "projectKey" and "fixVersion" here because these are set automatically.
     */
    void jqlToCollectReleaseNotes(String jqlToCollectReleaseNotes) {
        this.jqlToCollectReleaseNotes = jqlToCollectReleaseNotes
    }

    @Override
    protected Map<String, String> getConfig(Map<Object, Object> context) {
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
