package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class HerokuDeployWarTask extends Task {
    private static final TASK_ID = 'com.heroku.bamboo.heroku-bamboo-plugin:com.heroku.bamboo.WarDeploymentTask'
    private String apiKey
    private String appName
    private String warFile

    // for tests:
    protected HerokuDeployWarTask() {}

    HerokuDeployWarTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    /**
     * API Key.
     */
    void apiKey(String apiKey) {
        this.apiKey = apiKey
    }

    /**
     * App Name.
     */
    void appName(String appName) {
        this.appName = appName
    }

    /**
     * WAR File.
     */
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
