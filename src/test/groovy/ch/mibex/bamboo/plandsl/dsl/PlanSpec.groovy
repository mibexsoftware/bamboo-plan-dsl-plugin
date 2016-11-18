package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.AutoBranchManagement
import ch.mibex.bamboo.plandsl.dsl.branches.Branches
import ch.mibex.bamboo.plandsl.dsl.dependencies.AdvancedDependencyOptions
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependencies
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependency
import ch.mibex.bamboo.plandsl.dsl.variables.Variable
import ch.mibex.bamboo.plandsl.dsl.variables.Variables
import spock.lang.Specification

class PlanSpec extends Specification {

    def 'simple valid plan definition'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/SimplePlan.groovy').text))

        then:
        results != null
        results.projects.size() == 1
        results.projects[0].key == "SIMPLEPROJECT"
        results.projects[0].name == "Simple project"
        results.projects[0].plans[0] == new Plan(
                key: "SIMPLEPLAN",
                name: "Simple plan",
                enabled: true,
                description: "this is a simple plan"
        )
    }

    def 'plan with invalid plan key from DSL text'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/InvalidPlanKey.groovy').text))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:6): plan key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
    }

    def 'plan with invalid plan key from DSL script'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/InvalidPlanKey.groovy').text))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:6): plan key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
    }

    def 'plan without name should yield exception'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/InvalidPlanWithoutName.groovy').text))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:7): plan name must be specified'
    }

    def 'plan with variables'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/PlanVariables.groovy').text))

        then:
        results != null
        results.projects.size() == 1
        results.projects[0].key == "SIMPLEPROJECT"
        results.projects[0].name == "Simple project"
        results.projects[0].plans[0] == new Plan(
                key: "SIMPLEPLAN",
                name: "Simple plan",
                enabled: true,
                description: "this is a simple plan",
                variables: new Variables(variables: [new Variable('key1', 'value1'), new Variable('key2', 'value2')])
        )
    }

    def 'plans in external script'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/PlansInExternalScript.groovy').text))

        then:
        results != null
        results.projects[0].plans.size() == 3
        results.projects[0].plans[0].key == 'PLAN1'
        results.projects[0].plans[0].name == 'plan 1'

        def branches = new Branches(
                autoBranchManagement: new AutoBranchManagement(
                        inactiveBranchesStrategy: AutoBranchManagement.InactiveBranchesStrategy.DELETE_INACTIVE_PLAN_BRANCHES_AFTER_DAYS,
                        deleteInactivePlanBranchesAfterDays: 14,
                        newBranchesStrategy: AutoBranchManagement.NewBranchesStrategy.NEW_PLAN_BRANCHES_FOR_MATCHING_BRANCH_NAMES,
                        matchingBranchesRegex: "feature/*",
                        deletedBranchesStrategy: AutoBranchManagement.DeletedBranchesStrategy.DELETE_PLAN_BRANCHES_AFTER_DAYS,
                        deletePlanBranchesAfterDays: 7
                )
        )
        results.projects[0].plans[0].branches == branches
        results.projects[0].plans[1].branches == branches
        results.projects[0].plans[2].branches == branches
    }

    def 'plan with dependencies'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/PlanDependencies.groovy').text))

        then:
        results != null
        results.projects.size() == 1
        results.projects[0].key == "SIMPLEPROJECT"
        results.projects[0].name == "Simple project"
        results.projects[0].plans[0] == new Plan(
                key: "SIMPLEPLAN",
                name: "Simple plan",
                enabled: true,
                description: "this is a simple plan",
                dependencies: new Dependencies(
                        dependencies: [new Dependency('HELLO-HELLO'), new Dependency('SEED-SEED')],
                        blockingStrategy: Dependencies.DependencyBlockingStrategy.BLOCK_BUILD_IF_PARENT_BUILDS_ARE_QUEUED,
                        advancedOptions: new AdvancedDependencyOptions(
                                triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully: true,
                                autoDependencyManagement: true,
                                enableDependenciesForAllBranches: true
                        )
                )
        )
    }
}