package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes=['metaClass'], callSuper=true)
@ToString(includeFields=true)
class DockerTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.bamboo-docker-plugin:task.docker.cli'
    private String repository
    private DockerCommand command
    private boolean existingDockerfile
    private String dockerfileContents
    private boolean doNotUseCachingWhenBuildingImage
    private String saveTheImageAsFile
    private String environmentVariables
    private String workingSubDirectory

    //for tests
    protected DockerTask() {
        super(TASK_ID)
    }

    /**
     * @param repository Repository name (and optionally a tag) to be applied to the resulting image
     * (e.g. 'registry.address:port/namespace/repository:tag')
     * @param bambooFacade
     */
    DockerTask(String repository, BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
        this.repository = Validations.requireNotNullOrEmpty(repository, 'repository must not be empty')
    }

    /**
     * The Docker command to execute
     */
    void command(DockerCommand command) {
        this.command = command
    }

    /**
     * Use an existing Dockerfile located in the task's working directory
     */
    void useAnExistingDockerfile(enabled = true) {
        existingDockerfile = enabled
    }

    /**
     * Specify the Dockerfile contents
     */
    void dockerfileContents(String contents) {
        dockerfileContents = contents
    }

    /**
     * Do not use cache when building the image
     */
    void doNotUseCachingWhenBuildingImage(enabled = true) {
        doNotUseCachingWhenBuildingImage = enabled
    }

    /**
     * Save the image as a file
     */
    void saveTheImageAsFile(String fileName) {
        saveTheImageAsFile = fileName
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

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('commandOption', command.command)
        config.put('repository', repository)
        if (existingDockerfile) {
            config.put('dockerfileOption', 'existing')
        } else {
            config.put('dockerfileOption', 'inline')
            config.put('dockerfile', dockerfileContents)
        }
        config.put('nocache', doNotUseCachingWhenBuildingImage.toString())
        if (saveTheImageAsFile) {
            config.put('filename', saveTheImageAsFile)
            config.put('save', true.toString())
        }
        config.put('workingSubDirectory', workingSubDirectory)
        config.put('environmentVariables', environmentVariables)
        config
    }

    static enum DockerCommand {
        /**
         * Build a Docker image.
         */
        BUILD('build'),

        /**
         * Run a Docker container.
         */
        RUN('run'),

        /**
         * Push a Docker image to a Docker registry.
         */
        PUSH('push'),

        /**
         * Pull a Docker image to a Docker registry.
         */
        PULL('pull')

        String command

        DockerCommand(String command) {
            this.command = command
        }
    }

}
