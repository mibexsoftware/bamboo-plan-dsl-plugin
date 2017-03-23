package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class HerokuDeployWarTask extends Task {
    private static final TASK_ID = 'com.heroku.bamboo.heroku-bamboo-plugin:com.heroku.bamboo.WarDeploymentTask'
    private String apiKey
    private String appName
    private String warFile

    HerokuDeployWarTask() {
        super(TASK_ID)
    }

    @Deprecated
    HerokuDeployWarTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    HerokuDeployWarTask(String apiKey, String appName, String warFile, BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
        this.apiKey = Validations.isNotNullOrEmpty(apiKey, 'apiKey must not be empty')
        this.appName = Validations.isNotNullOrEmpty(appName, 'appName must not be empty')
        this.warFile = Validations.isNotNullOrEmpty(warFile, 'warFile must not be empty')
    }

    /**
     * API Key.
     *
     * @deprecated use {@link #HerokuDeployWarTask(String, String, String, BambooFacade)} instead
     */
    @Deprecated
    void apiKey(String apiKey) {
        this.apiKey = apiKey
    }

    /**
     * App Name.
     *
     * @deprecated use {@link #HerokuDeployWarTask(String, String, String, BambooFacade)} instead
     */
    @Deprecated
    void appName(String appName) {
        this.appName = appName
    }

    /**
     * WAR File.
     *
     * @deprecated use {@link #HerokuDeployWarTask(String, String, String, BambooFacade)} instead
     */
    @Deprecated
    void warFile(String warFile) {
        this.warFile = warFile
    }

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
        [
            'apiKey': apiKey,
            'appName': appName,
            'warFile': warFile
        ]
    }
}
