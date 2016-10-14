package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import ch.mibex.bamboo.plandsl.dsl.triggers.*
import spock.lang.Specification

class BranchSpecificSettingsSpec extends Specification {

    def 'branch specific settings'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/branches/BranchSpecificSettings.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].branches == new Branches(
                branches: [new Branch(
                        name: "develop",
                        description: "my developer branch",
                        enabled: true,
                        cleanupAutomatically: true,
                )]
        )
    }

    def 'branch specific scheduled trigger'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/branches/BranchSpecificScheduledTrigger.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].branches == new Branches(
                branches: [new Branch(
                        name: "develop",
                        triggers: new Triggers(
                                triggers: [new ScheduledTrigger(
                                        cronExpression: "0 15 10 * * ?",
                                        onlyRunIfOtherPlansArePassing: new OnlyIfOthersPassingTriggerCondition(
                                            planKeys: ["SEED-SEED-JOB1", "SEED-SEED-JOB2"]
                                        ),
                                )]
                        )
                )]
        )
    }

    def 'branch specific polling trigger cron'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/branches/BranchSpecificPollingTrigger.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].branches == new Branches(
                branches: [new Branch(
                        name: "develop",
                        triggers: new Triggers(
                                triggers: [new PollingTrigger(
                                        repositories: ["repo123", "repo456"],
                                        pollingStrategy: 'PERIOD',
                                        periodicTrigger: new PeriodicTrigger(
                                                pollingFrequencyInSecs: 30
                                        ),
                                        onlyRunIfOtherPlansArePassing: new OnlyIfOthersPassingTriggerCondition(
                                                planKeys: ["SEED-SEED-JOB1", "SEED-SEED-JOB2"]
                                        )
                                )]
                        )
                )]
        )
    }

}