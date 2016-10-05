package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class Maven3Task extends Task {
    static final TASK_ID = 'com.atlassian.bamboo.plugins.maven:task.builder.mvn3'
    String goal
    String executable
    String buildJdk
    String environmentVariables
    String workingSubDirectory
    boolean useMavenReturnCode
    String projectFile
    WithTests withTests
    private boolean hasTests

    Maven3Task() {
        super(TASK_ID)
    }

    void goal(String goal) {
        this.goal = goal
    }

    void executable(String executable) {
        this.executable = executable
    }

    void buildJdk(String buildJdk) {
        this.buildJdk = buildJdk
    }

    void withTests(@DelegatesTo(WithTests) Closure closure) {
        withTests = new WithTests()
        DslScriptHelper.execute(closure, withTests)
        hasTests = true
    }

    void environmentVariables(String environmentVariables) {
        this.environmentVariables = environmentVariables
    }

    void workingSubDirectory(String workingSubDirectory) {
        this.workingSubDirectory = workingSubDirectory
    }

    void useMavenReturnCode(boolean useMavenReturnCode) {
        this.useMavenReturnCode = useMavenReturnCode
    }

    void projectFile(String projectFile) {
        this.projectFile = projectFile
    }

    @Override
    def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('label', executable)
        config.put('executable', executable)
        config.put('goal', goal)
        config.put('buildJdk', buildJdk)
        config.put('environmentVariables', environmentVariables)
        config.put('workingSubDirectory', workingSubDirectory)
        config.put('testChecked', hasTests.toString())
        config.put('testResultsDirectory', withTests ? withTests.testResultsDirectory : '')
        config.put('testDirectoryOption',
                withTests?.testResultsDirectory ? 'customTestDirectory' : 'standardTestDirectory')
        config.put('useMavenReturnCode', useMavenReturnCode.toString())
        config.put('projectFile', projectFile)
        config
    }

}
