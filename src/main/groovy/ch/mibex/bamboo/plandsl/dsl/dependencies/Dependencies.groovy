package ch.mibex.bamboo.plandsl.dsl.dependencies

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Dependencies {
    Set<Dependency> dependencies = new LinkedHashSet<>()
    DependencyBlockingStrategy blockingStrategy
    AdvancedDependencyOptions advancedOptions

    static enum DependencyBlockingStrategy {
        DO_NOT_BLOCK,
        BLOCK_BUILD_IF_PARENT_BUILDS_ARE_QUEUED,
        BLOCK_BUILD_IF_PARENT_PLANS_HAVE_UNBUILT_CHANGES
    }

    void advancedOptions(@DelegatesTo(AdvancedDependencyOptions) Closure closure) {
        def advancedDependencyOptions = new AdvancedDependencyOptions()
        DslScriptHelper.execute(closure, advancedDependencyOptions)
        this.advancedOptions = advancedDependencyOptions
    }

    void blockingStrategy(DependencyBlockingStrategy blockingStrategy) {
        this.blockingStrategy = blockingStrategy
    }

    void dependency(String planKey) {
        Validations.isNotNullOrEmpty(planKey, 'Dependency plan key must not be empty')
        def dependency = new Dependency(planKey)
        dependencies << dependency
    }
}
