package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class PlansWithTriggersSpec extends Specification {

    def 'scheduled trigger'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/triggers/ScheduledTrigger.groovy').text
        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].triggers.triggers[0] == new ScheduledTrigger(
            description: "scheduled",
            cronExpression: "0 0 0 ? * *",
            onlyRunIfOtherPlansArePassing: new OnlyIfOthersPassingTriggerCondition(planKeys: ["PROJ-PLAN2"])
        )
    }

    def 'polling scheduled trigger'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/triggers/PollingScheduledTrigger.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].triggers.triggers[0] == new PollingTrigger(
            description: "mypollsched",
            repositories: ["mygit", "mybitbucket"],
            pollingStrategy: 'CRON',
            scheduledTrigger: new ScheduledTrigger(cronExpression: "0 0 0 ? * *"),
            onlyRunIfOtherPlansArePassing: new OnlyIfOthersPassingTriggerCondition(planKeys: ["PROJ-PLAN1"])
        )
    }

    def 'bitbucket server trigger'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/triggers/BitbucketServerTriggered.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].triggers.triggers[0] == new BitbucketServerTrigger(description: "run when new code")
    }

    def 'polling periodically trigger'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/triggers/PollingPeriodicallyTrigger.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].triggers.triggers[0] == new PollingTrigger(
            description: "mypollper",
            repositories: ["test2"],
            pollingStrategy: 'PERIOD',
            periodicTrigger: new PeriodicTrigger(pollingFrequencyInSecs: 180),
            onlyRunIfOtherPlansArePassing: new OnlyIfOthersPassingTriggerCondition(planKeys: ["PROJ-PLAN1", "PROJ-PLAN3", "PROJ-PLAN5"])
        )
    }

    def 'when changes are committed trigger'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/triggers/WhenCommitedTrigger.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].triggers.triggers[0] == new RemoteTrigger(
            description: "mycommit",
            repositories: ["test2"],
            ipAddresses: ["127.0.0.1", "192.168.0.1"],
            onlyRunIfOtherPlansArePassing: new OnlyIfOthersPassingTriggerCondition(planKeys: ["PROJ-PLAN1", "PROJ-PLAN3", "PROJ-PLAN5"])
        )
    }

    def 'multiple triggers'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/triggers/MultipleTriggers.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].triggers.triggers[0] == new PollingTrigger(
                description: "mypollsched",
                repositories: ["mygit", "mybitbucket"],
                pollingStrategy: 'CRON',
                scheduledTrigger: new ScheduledTrigger(cronExpression: "0 0 0 ? * *"),
                onlyRunIfOtherPlansArePassing: new OnlyIfOthersPassingTriggerCondition(planKeys: ["PROJ-PLAN1"])
        )
        results.projects[0].plans[0].triggers.triggers[1] == new RemoteTrigger(
                description: "mycommit",
                repositories: ["test2"],
                ipAddresses: ["127.0.0.1", "192.168.0.1"],
                onlyRunIfOtherPlansArePassing: new OnlyIfOthersPassingTriggerCondition(planKeys: ["PROJ-PLAN1", "PROJ-PLAN3", "PROJ-PLAN5"])
        )
    }
}