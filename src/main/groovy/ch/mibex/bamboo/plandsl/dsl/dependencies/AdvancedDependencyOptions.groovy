package ch.mibex.bamboo.plandsl.dsl.dependencies

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class AdvancedDependencyOptions extends BambooObject {
    private boolean enableDependenciesForAllBranches
    private boolean triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully
    private boolean autoDependencyManagement

    AdvancedDependencyOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected AdvancedDependencyOptions() {}

    /**
     * If disabled, dependencies will be triggered each time a build completes, even if there are subsequent
     * manual stages.
     */
    void triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully(boolean active = true) {
        triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully = active
    }

    /**
     * When enabled will provide automatic dependency management for supported executables (ie. Maven 2)
     */
    void autoDependencyManagement(boolean active = true) {
        autoDependencyManagement = active
    }

    /**
     * Dependencies are triggered for branches only if there is a branch with the same name within the dependent plan
     */
    void enableDependenciesForAllBranches(boolean active = true) {
        enableDependenciesForAllBranches = active
    }
}
