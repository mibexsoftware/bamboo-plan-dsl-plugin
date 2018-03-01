package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class NpmTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.bamboo-nodejs-plugin:task.builder.npm'
    private String executable
    private String command
    private String environmentVariables
    private String workingSubDirectory
    private boolean useIsolatedCache

    //for tests
    protected NpmTask() {
        super(TASK_ID)
    }

    /**
     * @param executable Node.js executable
     * @param command Command that npm should run (e.g. 'install', 'test')
     * @param bambooFacade
     */
    NpmTask(String executable, String command, BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
        this.executable = Validations.requireNotNullOrEmpty(executable, 'executable must not be empty')
        this.command = Validations.requireNotNullOrEmpty(command, 'command must not be empty')
    }

    /**
     * A temporary directory will be used as cache folder. Might slow down task execution.
     */
    void useIsolatedCache(boolean enabled = true) {
        useIsolatedCache = enabled
    }

    /**
     * Extra environment variables. e.g. MAVEN_OPTS="-Xmx256m -Xms128m". You can add multiple parameters separated by
     * a space.
     */
    void environmentVariables(String environmentVariables) {
        this.environmentVariables = environmentVariables
    }

    /**
     * Specify an alternative sub-directory as working directory for the task.
     */
    void workingSubDirectory(String workingSubDirectory) {
        this.workingSubDirectory = workingSubDirectory
    }

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('runtime', executable)
        config.put('command', command)
        config.put('isolatedCache', useIsolatedCache.toString())
        config.put('environmentVariables', environmentVariables)
        config.put('workingSubDirectory', workingSubDirectory)
        config
    }

}
