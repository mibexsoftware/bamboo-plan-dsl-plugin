package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class CommandTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.scripttask:task.builder.command'
    private String executable
    private String argument
    private String environmentVariables
    private String workingSubDirectory

    //for tests
    protected CommandTask() {
        super(TASK_ID)
    }

    CommandTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    /**
     * The executable to run.
     */
    void executable(String executableLabel) {
        //FIXME re-enable once we are also able to check non-shared capabilities
        //bambooFacade.requireExecutable(executableLabel)
        this.executable = executableLabel
    }

    /**
     * Argument you want to pass to the command. Arguments with spaces in them must be quoted.
     */
    void argument(String argument) {
        this.argument = argument
    }

    /**
     * Extra environment variables. e.g. JAVA_OPTS="-Xmx256m -Xms128m". You can add multiple parameters separated by
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
    protected Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('argument', argument)
        config.put('label', executable)
        config.put('workingSubDirectory', workingSubDirectory)
        config.put('environmentVariables', environmentVariables)
        config
    }

}
