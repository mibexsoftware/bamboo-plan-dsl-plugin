package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class MsBuildTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugin.dotnet:msbuild'
    private String executable
    private String projectFile
    private String environmentVariables
    private String workingSubDirectory
    private String options

    //for tests
    protected MsBuildTask() {
        super(TASK_ID)
    }

    /**
     * @param executable The MSBuild executable.
     * @param projectFile The Solution, Project File or MSBuild project to execute when this Job Builds.
     * @param bambooFacade
     */
    MsBuildTask(String executable, String projectFile, BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
        this.executable = Validations.isNotNullOrEmpty(executable, 'executable must not be empty')
        this.projectFile = Validations.isNotNullOrEmpty(projectFile, 'projectFile must not be empty')
    }

    /**
     * Extra environment variables. e.g. JAVA_OPTS="-Xmx256m -Xms128m". You can add multiple parameters
     * separated by a space.
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
     * The MSBuild.exe command line switches you wish to include.
     */
    void options(String options) {
        this.options = options
    }

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('solution', projectFile)
        config.put('options', options)
        config.put('environmentVariables', environmentVariables)
        config.put('workingSubDirectory', workingSubDirectory)
        config.put('label', executable)
        config
    }

}
