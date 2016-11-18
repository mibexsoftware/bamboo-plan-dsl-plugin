package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class StagesSpec extends Specification {

    def 'two stages'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/stages/TwoStages.groovy').text))

        then:
        def stages = results.projects[0].plans[0].children()
        stages.size() == 2
        stages[0].name == 'first stage'
        stages[1].name == 'second stage'
    }

    def 'stages in external script'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/stages/StagesInExternalScript.groovy').text))

        then:
        def stages = results.projects[0].plans[0].children()
        stages.size() == 2
        stages[0].name == 'local stage'
        stages[1].name == 'global stage'
        stages[0].children()[0].key == 'SIMPLEJOB'
        stages[1].children()[0].key == 'SIMPLEJOB'
    }

}