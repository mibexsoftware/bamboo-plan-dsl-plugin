package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class PlansWithBranchesSpec extends Specification {

    def 'autoBranchManagement settings'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/branches/AutoBranchManagement.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].branches == new Branches(
                autoBranchManagement: new AutoBranchManagement(
                        inactiveBranchesStrategy: AutoBranchManagement.InactiveBranchesStrategy.DELETE_INACTIVE_PLAN_BRANCHES_AFTER_DAYS,
                        deleteInactivePlanBranchesAfterDays: 14,
                        newBranchesStrategy: AutoBranchManagement.NewBranchesStrategy.NEW_PLAN_BRANCHES_FOR_PULL_REQUESTS,
                        deletedBranchesStrategy: AutoBranchManagement.DeletedBranchesStrategy.DELETE_PLAN_BRANCHES_AFTER_DAYS,
                        deletePlanBranchesAfterDays: 7,
                )
        )
    }

    def 'branch merging settings'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/branches/BranchesMergingConfig.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].branches == new Branches(
                branchMerging: new BranchMerging(
                        mergeStrategy: new GateKeeper(
                                planBranchKey: "SIMPLEPROJECT-SIMPLEPLAN",
                                pushEnabled: true
                        )
                )
        )
    }

    def 'branch notification settings'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/branches/BranchesNotificationSettings.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].branches == new Branches(
                notificationsType: Branch.NotifyOnNewBranchesType.DO_NOT_SEND_NOTIFICATIONS,
        )
    }

    def 'branch trigger settings'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/branches/BranchesTriggerSettings.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].branches == new Branches(
                newPlanBranchesTriggerType: Branches.NewPlanBranchesTriggerType.RUN_NEW_PLAN_BRANCHES_MANUALLY
        )
    }

    def 'multiple branches'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/branches/MultipleBranches.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].branches == new Branches(
                branches: [
                        new Branch(
                                name: 'develop',
                                description: 'my developer branch',
                                enabled: true,
                                cleanupAutomatically: true,
                        ),
                        new Branch(
                                name: 'release',
                                description: 'my release branch',
                                enabled: true,
                                cleanupAutomatically: true,
                        ),
                        new Branch(
                                name: 'test',
                                description: 'my test branch',
                                enabled: true,
                                cleanupAutomatically: true,
                        ),
                        new Branch(
                                name: 'feature_branch_123',
                                vcsBranchName: 'feature/branch_123',
                                enabled: true,
                                cleanupAutomatically: true,
                        )
                ]
        )
    }

    def 'external branches'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/branches/ExternalBranches.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].branches == new Branches(
                autoBranchManagement: new AutoBranchManagement(
                        inactiveBranchesStrategy: AutoBranchManagement.InactiveBranchesStrategy.DO_NOT_DELETE_INACTIVE_PLAN_BRANCHES,
                        deleteInactivePlanBranchesAfterDays: 30,
                        newBranchesStrategy: AutoBranchManagement.NewBranchesStrategy.NEW_PLAN_BRANCHES_FOR_MATCHING_BRANCH_NAMES,
                        matchingBranchesRegex: ".*",
                        deletedBranchesStrategy: AutoBranchManagement.DeletedBranchesStrategy.DO_NOT_DELETE_PLAN_BRANCHES,
                        deletePlanBranchesAfterDays: 0
                ),
                branches: [
                        new Branch(
                                name: 'develop',
                                description: 'my developer branch',
                                enabled: true,
                                cleanupAutomatically: false,
                                sourceRepository: new BranchSourceRepository(branchName: 'develop')
                        )
                ]
        )
    }

}