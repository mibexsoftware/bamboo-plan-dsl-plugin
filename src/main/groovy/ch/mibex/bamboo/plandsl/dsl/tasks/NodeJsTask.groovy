package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class NodeJsTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.bamboo-nodejs-plugin:task.builder.node'
    private String executable
    private String script
    private String environmentVariables
    private String workingSubDirectory
    private String arguments

    //for tests
    protected NodeJsTask() {
        super(TASK_ID)
    }

    /**
     * @param executable Node.js executable
     * @param script Script to execute with node (e.g. 'server.js', 'application.js'
     * @param bambooFacade
     */
    NodeJsTask(String executable, String script, BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
        this.executable = Validations.isNotNullOrEmpty(executable, 'executable must not be empty')
        this.script = Validations.isNotNullOrEmpty(script, 'script must not be empty')
    }

    /**
     * Additional command line arguments to pass to node when executing the script.
     */
    void arguments(String arguments) {
        this.arguments = arguments
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
        config.put('command', script)
        config.put('arguments', arguments)
        config.put('environmentVariables', environmentVariables)
        config.put('workingSubDirectory', workingSubDirectory)
        config
    }

}
