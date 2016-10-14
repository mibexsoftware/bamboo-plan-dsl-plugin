package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class VcsCheckoutTask extends Task {
    static final TASK_ID = 'com.atlassian.bamboo.plugins.vcs:task.vcs.checkout'
    boolean forceCleanBuild
    Set<CheckoutRepository> repositories = new LinkedHashSet<>()

    VcsCheckoutTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    void forceCleanBuild(boolean forceCleanBuild) {
        this.forceCleanBuild = forceCleanBuild
    }

    // multiple repositories possible!
    void repository(String name, @DelegatesTo(CheckoutRepository) Closure closure) {
        def repo = new CheckoutRepository(name)
        DslScriptHelper.execute(closure, repo)
        repositories << repo
    }

    @Override
    def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        def repoIdMappings = context.get('repositories') as Map<String, String>
        repositories.eachWithIndex { item, index ->
            config.put('checkoutDir_' + index, item.checkoutDirectory)
            def id = repoIdMappings.get(item.name)
            Validations.isNotNullOrEmpty(id, "could not find a repository for ${item.name}")
            config.put('selectedRepository_' + index, id)
        }
        config.put('cleanCheckout', forceCleanBuild.toString())
        config
    }

}
