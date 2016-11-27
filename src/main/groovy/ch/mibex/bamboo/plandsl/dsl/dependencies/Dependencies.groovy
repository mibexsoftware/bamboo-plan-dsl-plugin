package ch.mibex.bamboo.plandsl.dsl.dependencies

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Dependencies extends BambooObject {
    private List<Dependency> dependencies = []
    private DependencyBlockingStrategy blockingStrategy
    private AdvancedDependencyOptions advancedOptions

    Dependencies(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected Dependencies() {}

    static enum DependencyBlockingStrategy {
        /**
         * Builds for this plan will run whenever they are triggered, regardless of whether they have parent builds
         * or not.
         */
        DO_NOT_BLOCK,

        /**
         * If parent builds are queued or building, the current build will be blocked.
         */
        BLOCK_BUILD_IF_PARENT_BUILDS_ARE_QUEUED,

        /**
         * If parent plans have changes, parent builds will be triggered and the current build blocked. The current
         * build is also blocked if parent builds are queued or building.
         */
        BLOCK_BUILD_IF_PARENT_PLANS_HAVE_UNBUILT_CHANGES
    }

    void advancedOptions(@DelegatesTo(AdvancedDependencyOptions) Closure closure) {
        def advancedDependencyOptions = new AdvancedDependencyOptions()
        DslScriptHelper.execute(closure, advancedDependencyOptions)
        this.advancedOptions = advancedDependencyOptions
    }

    /**
     * Dependency blocking is useful for ensuring that a 'tree' of dependent builds always runs in order of the
     * tree hierarchy, even if child builds are triggered independently of their parents.
     */
    void blockingStrategy(DependencyBlockingStrategy blockingStrategy) {
        this.blockingStrategy = blockingStrategy
    }

    void dependency(String planKey) {
        Validations.isNotNullOrEmpty(planKey, 'Dependency plan key must not be empty')
        def dependency = new Dependency(planKey, bambooFacade)
        dependencies << dependency
    }

}
