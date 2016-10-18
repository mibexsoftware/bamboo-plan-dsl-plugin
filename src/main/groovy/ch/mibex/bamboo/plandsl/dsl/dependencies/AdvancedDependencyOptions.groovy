package ch.mibex.bamboo.plandsl.dsl.dependencies

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class AdvancedDependencyOptions {
    boolean enableDependenciesForAllBranches
    boolean triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully
    boolean autoDependencyManagement

    void triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully(boolean active) {
        triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully = active
    }

    void autoDependencyManagement(boolean active) {
        autoDependencyManagement = active
    }

    void enableDependenciesForAllBranches(boolean active) {
        enableDependenciesForAllBranches = active
    }
}
