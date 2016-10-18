package ch.mibex.bamboo.plandsl.dsl

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
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/InvalidPlanKey.txt').text))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:6): plan key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
    }


    def 'plan with invalid plan key from DSL script'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def resource = getClass().getResource('/dsls/plans/InvalidPlanKey.txt')
        loader.parse(new DslScriptContext(new File(resource.toURI()).absolutePath, null, null))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(InvalidPlanKey.txt:6): plan key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
    }

    def 'plan without name should yield exception'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/plans/PlanWithoutName.txt').text))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:6): Plan must have a name attribute'
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