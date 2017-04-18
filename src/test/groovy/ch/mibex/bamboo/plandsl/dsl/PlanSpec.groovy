package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.AutoBranchManagement
import ch.mibex.bamboo.plandsl.dsl.branches.Branches
import ch.mibex.bamboo.plandsl.dsl.dependencies.AdvancedDependencyOptions
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependencies
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependency
import ch.mibex.bamboo.plandsl.dsl.permissions.PermissionTypes
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import ch.mibex.bamboo.plandsl.dsl.plans.ExpirationDetails
import ch.mibex.bamboo.plandsl.dsl.plans.Miscellaneous
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
        results.projects[0].projectKey == "SIMPLEPROJECT"
        results.projects[0].projectName == "Simple project"
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
        e.message == '(script:6): key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
    }

    def 'plan with invalid plan key from DSL script'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/InvalidPlanKey.groovy').text))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:6): key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
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
        results.projects[0].projectKey == "SIMPLEPROJECT"
        results.projects[0].projectName == "Simple project"
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
        results.projects[0].plans.size() == 2
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
    }

    def 'plan with dependencies'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/PlanDependencies.groovy').text))

        then:
        results != null
        results.projects.size() == 1
        results.projects[0].projectKey == "SIMPLEPROJECT"
        results.projects[0].projectName == "Simple project"
        results.projects[0].plans[0] == new Plan(
                key: "SIMPLEPLAN",
                name: "Simple plan",
                enabled: true,
                description: "this is a simple plan",
                dependencies: new Dependencies(
                        dependencies: [new Dependency(planKey: 'HELLO-HELLO'), new Dependency(planKey: 'SEED-SEED')],
                        blockingStrategy: Dependencies.DependencyBlockingStrategy.BLOCK_BUILD_IF_PARENT_BUILDS_ARE_QUEUED,
                        advancedOptions: new AdvancedDependencyOptions(
                                triggerDependenciesOnlyWhenAllStagesHaveRunSuccessfully: true,
                                autoDependencyManagement: true,
                                enableDependenciesForAllBranches: true
                        )
                )
        )
    }

    def 'plan with new syntax should yield correct plan'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def result = loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/PlanNewSyntax.groovy').text))

        then:
        result.projects.size() == 1
        result.projects[0].projectKey == 'SIMPLEPROJECT'
        result.projects[0].projectName == 'Simple project'
        result.projects[0].plans[0] == new Plan(
                key: "SIMPLEPLAN",
                name: "Simple plan",
                enabled: true,
                description: "this is a simple plan"
        )
    }

    def 'plan with permissions'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def result = loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/PlanWithPermissions.groovy').text))

        then:
        result.projects.size() == 1
        result.projects[0].projectKey == 'SIMPLEPROJECT'
        result.projects[0].projectName == 'Simple project'
        result.projects[0].plans[0] == new Plan(
                key: "SIMPLEPLAN",
                name: "Simple plan",
                enabled: true,
                description: "this is a simple plan",
                permissions: new Permissions(
                    userPermissions: ['diego': new PermissionTypes(permissionTypes: [PermissionTypes.PermissionType.ADMIN])],
                    groupPermissions: ['devops': new PermissionTypes(permissionTypes: [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.BUILD])],
                    otherPermissions: ['ROLE_ANONYMOUS': new PermissionTypes(permissionTypes: [PermissionTypes.PermissionType.VIEW, PermissionTypes.PermissionType.ADMIN])],
                )
        )
    }

    def 'plan with miscellaneous options'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def result = loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/Miscellaneous.groovy').text))

        then:
        result.projects.size() == 1
        result.projects[0].plans[0] == new Plan(
                key: "SIMPLEPLAN",
                name: "Simple plan",
                enabled: true,
                description: "this is a simple plan",
                miscellaneous: new Miscellaneous(
                        doNotExpireAnything: false,
                        expirationDetails: new ExpirationDetails(
                                expireBuildResults: true,
                                expireBuildLogs: true,
                                expireBuildArtifacts: false,
                                expireAfter: 10,
                                expireTimeUnit: ExpirationDetails.TimeUnit.WEEKS,
                                minimumBuildsToKeep: 12,
                                keepBuildsWithLabels: ["DONOTDELETE", "IMPORTANT"]
                        ),
                        customSettings: [
                                'custom.ruby-config-runtime': 'SYSTEM 2.0.0-p648@default',
                                'custom.ruby-config-environmentVariables': 'SOME_VAR="-D123 -R345"'
                        ]
                )
        )
    }
}