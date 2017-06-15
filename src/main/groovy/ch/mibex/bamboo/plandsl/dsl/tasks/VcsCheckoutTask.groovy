package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Stage
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class VcsCheckoutTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.vcs:task.vcs.checkout'
    private boolean forceCleanBuild
    private List<CheckoutRepository> repositories = []

    @Deprecated
    VcsCheckoutTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    protected VcsCheckoutTask() {
        super(TASK_ID)
    }

    /**
     * Removes the source directory and checks it out again prior to each build. This may significantly increase
     * build times.
     */
    void forceCleanBuild(boolean forceCleanBuild = true) {
        this.forceCleanBuild = forceCleanBuild
    }

    /**
     * You can call this multiple times for all the repositories you want to checkout. Default always points to
     * Plans default repository.
     */
    void repository(String name,
                    @DelegatesTo(value = CheckoutRepository, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def repo = new CheckoutRepository(name, bambooFacade)
        DslScriptHelper.execute(closure, repo)
        repositories << repo
    }

    /**
     * You can call this multiple times for all the repositories you want to checkout. Default always points to
     * Plans default repository.
     *
     * @param params the properties for the repository. Currently, only "name" is expected.
     */
    Stage repository(Map<String, String> params,
                     @DelegatesTo(value = CheckoutRepository, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        repository(params['name'], closure)
    }

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        def repoIdMappings = context.get('repositories') as Map<String, String>
        repositories.eachWithIndex { item, index ->
            config.put('checkoutDir_' + index, item.checkoutDirectory)
            def id = repoIdMappings.get(item.name)
            Validations.isNotNullOrEmpty(id, "Could not find a repository for '${item.name}'")
            config.put('selectedRepository_' + index, id)
        }
        config.put('cleanCheckout', String.valueOf(forceCleanBuild))
        config
    }

}
