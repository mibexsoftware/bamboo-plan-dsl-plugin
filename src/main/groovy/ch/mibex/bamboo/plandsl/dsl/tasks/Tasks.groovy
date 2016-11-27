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
    protected Tasks() {}

    protected Tasks(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Execute a script (e.g. Shell, Bash, PowerShell, Python) from the command line.
     */
    void script(String description, @DelegatesTo(ScriptTask) Closure closure) {
        handleTask(closure, ScriptTask, description)
    }

    /**
     * Execute a globally defined command.
     */
    void command(String description, @DelegatesTo(CommandTask) Closure closure) {
        handleTask(closure, CommandTask, description)
    }

    /**
     * Injects Bamboo variables from a file with a simple "key=value" format.
     */
    void injectBambooVariables(String description, @DelegatesTo(InjectBambooVariablesTask) Closure closure) {
        handleTask(closure, InjectBambooVariablesTask, description)
    }

    /**
     * Checkout from a repository.
     */
    void checkout(String description, @DelegatesTo(VcsCheckoutTask) Closure closure) {
        handleTask(closure, VcsCheckoutTask, description)
    }

    void cleanWorkingDirectory(String description, @DelegatesTo(CleanWorkingDirTask) Closure closure) {
        handleTask(closure, CleanWorkingDirTask, description)
    }

    /**
     * Copy Bamboo shared artifact to agent working directory.
     */
    void artifactDownload(String description, @DelegatesTo(ArtifactDownloaderTask) Closure closure) {
        handleTask(closure, ArtifactDownloaderTask, description)
    }

    /**
     * Deploys an Atlassian plugin to a remote application server.
     */
    @RequiresPlugin(key = 'com.atlassian.bamboo.plugins.deploy.continuous-plugin-deployment')
    void deployPlugin(String description, @DelegatesTo(DeployPluginTask) Closure closure) {
        handleTask(closure, DeployPluginTask, description)
    }

    /**
     * Deploys your plug-ins to the Atlassian Marketplace.
     */
    @RequiresPlugin(key = 'ch.mibex.bamboo.shipit2mpac')
    void shipit2marketplace(String description, @DelegatesTo(ShipItPluginTask) Closure closure) {
        handleTask(closure, ShipItPluginTask, description)
    }

    /**
     * Execute one or more Maven 3 goals as part of your build.
     */
    void maven3(String description, @DelegatesTo(Maven3Task) Closure closure) {
        handleTask(closure, Maven3Task, description)
    }

    /**
     * Deploy a WAR artifact to Heroku.
     */
    void herokuDeployWar(String description, @DelegatesTo(HerokuDeployWarTask) Closure closure) {
        handleTask(closure, HerokuDeployWarTask, description)
    }

    /**
     * A custom task not natively supported.
     */
    void custom(String pluginKey, @DelegatesTo(CustomTask) Closure closure) {
        def task = new CustomTask(bambooFacade, pluginKey)
        DslScriptHelper.execute(closure, task)
        tasks << task
    }

    private void handleTask(Closure closure, Class<? extends Task> clazz, String description) {
        def task = clazz.newInstance(bambooFacade)
        task.description = description
        DslScriptHelper.execute(closure, task)
        tasks << task
    }
}
