package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.AbstractBambooElement
import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslParentElement
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.RequiresPlugin
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Tasks extends AbstractBambooElement implements DslParentElement<Task> {
    List<Task> tasks = []

    // for tests
    protected Tasks() {}

    protected Tasks(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void script(String description, @DelegatesTo(ScriptTask) Closure closure) {
        handleTask(closure, ScriptTask, description)
    }

    void command(String description, @DelegatesTo(CommandTask) Closure closure) {
        handleTask(closure, CommandTask, description)
    }

    void injectBambooVariables(String description, @DelegatesTo(InjectBambooVariablesTask) Closure closure) {
        handleTask(closure, InjectBambooVariablesTask, description)
    }

    void checkout(String description, @DelegatesTo(VcsCheckoutTask) Closure closure) {
        handleTask(closure, VcsCheckoutTask, description)
    }

    void cleanWorkingDirectory(String description, @DelegatesTo(CleanWorkingDirTask) Closure closure) {
        handleTask(closure, CleanWorkingDirTask, description)
    }

    void artifactDownload(String description, @DelegatesTo(ArtifactDownloaderTask) Closure closure) {
        handleTask(closure, ArtifactDownloaderTask, description)
    }

    @RequiresPlugin(key = "com.atlassian.bamboo.plugins.deploy.continuous-plugin-deployment")
    void deployPlugin(String description, @DelegatesTo(DeployPluginTask) Closure closure) {
        handleTask(closure, DeployPluginTask, description)
    }

    void maven3(String description, @DelegatesTo(Maven3Task) Closure closure) {
        handleTask(closure, Maven3Task, description)
    }

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

    @Override
    Collection<Task> children() {
        tasks
    }
}
