package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class HerokuDeployWarTask extends Task {
    static final TASK_ID = 'com.heroku.bamboo.heroku-bamboo-plugin:com.heroku.bamboo.WarDeploymentTask'
    String apiKey
    String appName
    String warFile

    // for tests:
    protected HerokuDeployWarTask() {}

    HerokuDeployWarTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    void apiKey(String apiKey) {
        this.apiKey = apiKey
    }

    void appName(String appName) {
        this.appName = appName
    }

    void warFile(String warFile) {
        this.warFile = warFile
    }

    @Override
    def Map<String, String> getConfig(Map<Object, Object> context) {
        [
            'apiKey': apiKey,
            'appName': appName,
            'warFile': warFile
        ]
    }
}
