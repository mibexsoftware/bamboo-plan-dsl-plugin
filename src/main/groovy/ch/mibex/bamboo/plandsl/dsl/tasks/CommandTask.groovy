package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class CommandTask extends Task {
    static final TASK_ID = 'com.atlassian.bamboo.plugins.scripttask:task.builder.command'
    String executable
    String argument
    String environmentVariables
    String workingSubDirectory

    //for tests
    protected CommandTask() {}

    protected CommandTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    void executable(String executableLabel) {
        bambooFacade.requireExecutable(executableLabel)
        this.executable = executableLabel
    }

    void argument(String argument) {
        this.argument = argument
    }

    void environmentVariables(String environmentVariables) {
        this.environmentVariables = environmentVariables
    }

    void workingSubDirectory(String workingSubDirectory) {
        this.workingSubDirectory = workingSubDirectory
    }

    @Override
    Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('argument', argument)
        config.put('label', executable)
        config.put('workingSubDirectory', workingSubDirectory)
        config.put('environmentVariables', environmentVariables)
        config
    }

}
