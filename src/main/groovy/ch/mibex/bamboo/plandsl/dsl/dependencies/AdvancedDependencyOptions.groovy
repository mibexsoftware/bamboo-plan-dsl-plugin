package ch.mibex.bamboo.plandsl.dsl.dependencies

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class AdvancedDependencyOptions {
    boolean enableDependenciesForAllBranches
    boolean triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully
    boolean autoDependencyManagement

    void triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully(boolean active = true) {
        triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully = active
    }

    void autoDependencyManagement(boolean active = true) {
        autoDependencyManagement = active
    }

    void enableDependenciesForAllBranches(boolean active = true) {
        enableDependenciesForAllBranches = active
    }
}
