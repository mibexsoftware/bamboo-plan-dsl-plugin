package ch.mibex.bamboo.plandsl.dsl.tasks

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

    CommandTask() {
        super(TASK_ID)
    }

    void executable(String executable) {
        this.executable = executable
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
