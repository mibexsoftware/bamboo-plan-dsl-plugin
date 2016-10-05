package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class VcsCheckoutTask extends Task {
    static final TASK_ID = 'com.atlassian.bamboo.plugins.vcs:task.vcs.checkout'
    boolean forceCleanBuild
    Set<CheckoutRepository> repositories = new LinkedHashSet<>()

    VcsCheckoutTask() {
        super(TASK_ID)
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
        repositories.eachWithIndex { item, index ->
            config.put('checkoutDir_' + index, item.checkoutDirectory)
            config.put('selectedRepository_' + index, (
                    context.get('repositories') as Map<String, String>).get(item.name))
        }
        config.put('cleanCheckout', forceCleanBuild.toString())
        config
    }

}
