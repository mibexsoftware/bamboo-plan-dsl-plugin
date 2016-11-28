package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class CleanWorkingDirTask extends Task {
    private static final TASK_ID =
            'com.atlassian.bamboo.plugins.bamboo-artifact-downloader-plugin:cleanWorkingDirectoryTask'

    CleanWorkingDirTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    @Override
    protected Map<String, String> getConfig(Map<Object, Object> context) { [:] }
}
