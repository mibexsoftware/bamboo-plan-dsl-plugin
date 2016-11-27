package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.NullBambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class Maven3Task extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.maven:task.builder.mvn3'
    private String goal
    private String executable
    private String buildJdk
    private String environmentVariables
    private String workingSubDirectory
    private boolean useMavenReturnCode
    private String projectFile
    private WithTests withTests
    private boolean hasTests

    //for tests
    protected Maven3Task() {
        this(new NullBambooFacade())
    }

    Maven3Task(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    /**
     * The goal you want to execute. You can also define system properties such as -Djava.Awt.Headless=true.
     */
    void goal(String goal) {
        this.goal = goal
    }

    /**
     * Maven executable.
     */
    void executable(String executableLabel) {
        bambooFacade.requireExecutable(executableLabel)
        this.executable = executableLabel
    }

    /**
     * Which JDK do you need to use for the build? the JAVA_HOME will be added as an environment variable.
     */
    void buildJdk(String buildJdkLabel) {
        bambooFacade.requireJdk(buildJdkLabel)
        this.buildJdk = buildJdkLabel
    }

    /**
     * If checked, the build will fail if no tests are found. Test output must be in JUnit XML format.
     */
    void withTests(@DelegatesTo(WithTests) Closure closure) {
        withTests = new WithTests()
        DslScriptHelper.execute(closure, withTests)
        hasTests = true
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

    /**
     * When determining build success, Bamboo checks Maven return code and searches the log for "build success".
     * By checking this option, you will configure Bamboo to skip log parsing. This may fail on some Maven
     * versions/operating systems.
     */
    void useMavenReturnCode(boolean useMavenReturnCode = true) {
        this.useMavenReturnCode = useMavenReturnCode
    }

    /**
     * Path to the project file, relative to the working sub directory. If left blank Maven will use the pom.xml in
     * the root of the working sub directory
     */
    void projectFile(String projectFile) {
        this.projectFile = projectFile
    }

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
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
