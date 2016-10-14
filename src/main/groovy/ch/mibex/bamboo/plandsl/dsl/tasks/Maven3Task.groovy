package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
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

    //for tests
    protected Maven3Task() {}

    Maven3Task(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    void goal(String goal) {
        this.goal = goal
    }

    void executable(String executableLabel) {
        bambooFacade.requireExecutable(executableLabel)
        this.executable = executableLabel
    }

    void buildJdk(String buildJdkLabel) {
        bambooFacade.requireJdk(buildJdkLabel)
        this.buildJdk = buildJdkLabel
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
