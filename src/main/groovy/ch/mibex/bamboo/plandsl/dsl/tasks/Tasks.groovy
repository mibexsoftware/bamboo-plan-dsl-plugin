package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.RequiresPlugin
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Tasks extends BambooObject {
    private List<Task> tasks = []

    // for tests
    protected Tasks() {
    }

    Tasks(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Execute a script (e.g. Shell, Bash, PowerShell, Python) from the command line.
     *
     * @deprecated use {@link #script(Closure)} instead
     */
    @Deprecated
    void script(String description,
                @DelegatesTo(value = ScriptTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, ScriptTask, description)
    }

    /**
     * Execute a script (e.g. Shell, Bash, PowerShell, Python) from the command line.
     */
    void script(@DelegatesTo(value = ScriptTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, ScriptTask)
    }

    /**
     * Execute a globally defined command.
     *
     * @deprecated use {@link #command(Closure)} instead
     */
    @Deprecated
    void command(String description,
                 @DelegatesTo(value = CommandTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, CommandTask, description)
    }

    /**
     * Execute a globally defined command.
     */
    void command(@DelegatesTo(value = CommandTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, CommandTask)
    }

    /**
     * Injects Bamboo variables from a file with a simple "key=value" format.
     *
     * @deprecated use {@link #injectBambooVariables(Map, Closure)} instead
     */
    @Deprecated
    void injectBambooVariables(
            String description,
            @DelegatesTo(value = InjectBambooVariablesTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, InjectBambooVariablesTask, description)
    }

    /**
     * Injects Bamboo variables from a file with a simple "key=value" format.
     *
     * @param propertiesFilePath path to properties file. Each line of the file should be in the form of "key=value"
     * @param namespace is used to avoid name conflicts with existing variables.

     */
    void injectBambooVariables(
            String propertiesFilePath, String namespace,
            @DelegatesTo(value = InjectBambooVariablesTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new InjectBambooVariablesTask(propertiesFilePath, namespace, bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Injects Bamboo variables from a file with a simple "key=value" format.
     *
     * @param params The mandatory parameters for this task. "propertiesFilePath" and "namespace" are expected.
     */
    void injectBambooVariables(
            Map<String, String> params,
            @DelegatesTo(value = InjectBambooVariablesTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        injectBambooVariables(params['propertiesFilePath'], params['namespace'], closure)
    }

    /**
     * Checkout from a repository.
     *
     * @deprecated use {@link #checkout(Closure)} instead
     */
    @Deprecated
    void checkout(String description,
                  @DelegatesTo(value = VcsCheckoutTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, VcsCheckoutTask, description)
    }

    /**
     * Checkout from a repository.
     */
    void checkout(@DelegatesTo(value = VcsCheckoutTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, VcsCheckoutTask)
    }

    /**
     * @deprecated use {@link #cleanWorkingDirectory(Closure)} instead
     */
    @Deprecated
    void cleanWorkingDirectory(String description,
                               @DelegatesTo(value = CleanWorkingDirTask, strategy = Closure.DELEGATE_FIRST) Closure c) {
        handleTask(c, CleanWorkingDirTask, description)
    }

    void cleanWorkingDirectory(
            @DelegatesTo(value = CleanWorkingDirTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, CleanWorkingDirTask)
    }

    /**
     * Copy Bamboo shared artifact to agent working directory.
     *
     * @deprecated use {@link #artifactDownload(Closure)} instead
     */
    @Deprecated
    void artifactDownload(String description,
                          @DelegatesTo(value = ArtifactDownloaderTask, strategy = Closure.DELEGATE_FIRST) Closure c) {
        handleTask(c, ArtifactDownloaderTask, description)
    }

    /**
     * Copy Bamboo shared artifact to agent working directory.
     */
    void artifactDownload(@DelegatesTo(value = ArtifactDownloaderTask, strategy = Closure.DELEGATE_FIRST) Closure c) {
        handleTask(c, ArtifactDownloaderTask)
    }

    /**
     * Deploys an Atlassian plugin to a remote application server.
     *
     * @deprecated use {@link #deployPlugin(Map, Closure)} instead
     */
    @RequiresPlugin(key = 'com.atlassian.bamboo.plugins.deploy.continuous-plugin-deployment')
    @Deprecated
    void deployPlugin(String description,
                      @DelegatesTo(value = DeployPluginTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, DeployPluginTask, description)
    }

    /**
     * Deploys an Atlassian plugin to a remote application server.
     *
     * @param deployArtifactName select a Bamboo artifact to deploy. The artifact should be a single plugin jar file
     * @param productType the Atlassian product type to deploy the artifact to
     * @param deployURL the address of the remote Atlassian application where the plugin will be installed
     * @param deployUsername user name to deploy
     * @param deployPasswordVariable a Bamboo variable with the password for this user to deploy
     */
    @RequiresPlugin(key = 'com.atlassian.bamboo.plugins.deploy.continuous-plugin-deployment')
    void deployPlugin(String deployArtifactName, DeployPluginTask.ProductType productType, String deployURL,
                      String deployUsername, String deployPasswordVariable,
                      @DelegatesTo(value = DeployPluginTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new DeployPluginTask(
                deployArtifactName, productType, deployURL, deployUsername, deployPasswordVariable, bambooFacade
        )
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Deploys an Atlassian plugin to a remote application server.
     *
     * @param params the mandatory parameters for this task. "deployArtifactName", "productType",
     * "deployURL", "deployUsername" and "deployPasswordVariable" are expected.
     */
    @RequiresPlugin(key = 'com.atlassian.bamboo.plugins.deploy.continuous-plugin-deployment')
    void deployPlugin(Map<String, Object> params,
                      @DelegatesTo(value = DeployPluginTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        deployPlugin(
                params['deployArtifactName'] as String, params['productType'] as DeployPluginTask.ProductType,
                params['deployURL'] as String, params['deployUsername'] as String,
                params['deployPasswordVariable'] as String, closure
        )
    }

    /**
     * Deploys your plug-ins to the Atlassian Marketplace.
     *
     * @deprecated use {@link #shipIt2marketplace(Map, Closure)} instead
     */
    @Deprecated
    @RequiresPlugin(key = 'ch.mibex.bamboo.shipit2mpac')
    void shipit2marketplace(String description,
                            @DelegatesTo(value = ShipItPluginTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, ShipItPluginTask, description)
    }

    /**
     * Deploys your plug-ins to the Atlassian Marketplace.
     *
     * @param deployArtifactName artifact to publish to the Atlassian Marketplace.
     */
    @RequiresPlugin(key = 'ch.mibex.bamboo.shipit2mpac')
    void shipIt2marketplace(String deployArtifactName,
                            @DelegatesTo(value = ShipItPluginTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new ShipItPluginTask(deployArtifactName, bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Deploys your plug-ins to the Atlassian Marketplace.
     *
     * @param params The mandatory parameters for this task. Only "deployArtifactName" is expected.
     */
    @RequiresPlugin(key = 'ch.mibex.bamboo.shipit2mpac')
    void shipIt2marketplace(Map<String, String> params,
                            @DelegatesTo(value = ShipItPluginTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        shipIt2marketplace(params['deployArtifactName'], closure)
    }

    /**
     * Execute one or more Maven 3 goals as part of your build.
     *
     * @deprecated use {@link #maven3x(Map, Closure)} instead
     */
    @Deprecated
    void maven3(String description,
                @DelegatesTo(value = Maven3Task, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, Maven3Task, description)
    }

    /**
     * Execute one or more Maven 3 goals as part of your build.
     *
     * @param goal The goal you want to execute. You can also define system properties such as -Djava.Awt.Headless=true.
     */
    void maven3x(String goal, @DelegatesTo(value = Maven3Task, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new Maven3Task(goal, bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Execute JavaScript on the server with Node.js
     *
     * @params params The mandatory parameters for this task. "executable" and "script" are expected.
     */
    void nodeJs(Map<String, String> params,
                @DelegatesTo(value = NodeJsTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new NodeJsTask(params['executable'], params['script'], bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * npm package manager for Node.js
     *
     * @params params The mandatory parameters for this task. "executable" and "command" are expected.
     */
    void npm(Map<String, String> params,
             @DelegatesTo(value = NpmTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new NpmTask(params['executable'], params['command'], bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Execute one or more Maven 3 goals as part of your build.
     *
     * @params params The mandatory parameters for this task. Only "goal" is expected.
     */
    void maven3x(Map<String, String> params,
                 @DelegatesTo(value = Maven3Task, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        maven3x(params['goal'], closure)
    }

    /**
     * Run MSBuild as part of your build.
     *
     * @params params The mandatory parameters for this task. "executable" and "projectFile" are expected.
     */
    void msbuild(Map<String, String> params,
                 @DelegatesTo(value = MsBuildTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new MsBuildTask(params['executable'], params['projectFile'], bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Parses and displays JUnit test results.
     */
    void junitParser(@DelegatesTo(value = JUnitParserTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new JUnitParserTask(bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Copy files to a remote server using SCP.
     *
     * @params params The mandatory parameters for this task. "host" and "userName" are expected.
     */
    void scp(Map<String, String> params,
             @DelegatesTo(value = ScpTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new ScpTask(params['host'], params['userName'], bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Run a remote command over SSH.
     *
     * @params params The mandatory parameters for this task. "host" and "userName" are expected.
     */
    void ssh(Map<String, String> params,
             @DelegatesTo(value = SshTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new SshTask(params['host'], params['userName'], bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Build, run and deploy Docker containers using the Docker command line interface.
     *
     * @params params The mandatory parameters for this task. Only "repository" is expected.
     */
    void docker(Map<String, String> params,
                @DelegatesTo(value = DockerTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new DockerTask(params['repository'], bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Deploy a WAR artifact to Heroku.
     *
     * @deprecated use {@link #herokuDeployWar(Map, Closure)} instead
     */
    @Deprecated
    void herokuDeployWar(String description,
                         @DelegatesTo(value = HerokuDeployWarTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleTask(closure, HerokuDeployWarTask, description)
    }

    /**
     * Deploy a WAR artifact to Heroku.
     *
     * @param apiKey API key
     * @param appName application name
     * @param warFile WAR file
     */
    void herokuDeployWar(String apiKey, String appName, String warFile,
                         @DelegatesTo(value = HerokuDeployWarTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new HerokuDeployWarTask(apiKey, appName, warFile, bambooFacade)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * Deploy a WAR artifact to Heroku.
     *
     * @param params The mandatory parameters for this task. "apiKey", "appName" and "warFile" are expected.
     */
    void herokuDeployWar(Map<String, String> params,
                         @DelegatesTo(value = HerokuDeployWarTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        herokuDeployWar(params['apiKey'], params['appName'], params['warFile'], closure)
    }

    /**
     * A custom task not natively supported.
     */
    void custom(String pluginKey, @DelegatesTo(value = CustomTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def task = new CustomTask(bambooFacade, pluginKey)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    /**
     * A custom task not natively supported.
     *
     * @params params The mandatory parameters for this task. Only "pluginKey" is expected.
     */
    void custom(Map<String, String> params,
                @DelegatesTo(value = CustomTask, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        custom(params['pluginKey'], closure)
    }

    private void handleTask(Closure closure, Class<? extends Task> clazz, String description = null) {
        def task = clazz.newInstance(bambooFacade)
        task.description = description
        DslScriptHelper.execute(closure, task)
        tasks << task
    }
}
