package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class ProjectSpec extends Specification {

    def 'project without name should yield exception'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/projects/ProjectWithoutName.txt').text))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:3): Project must have a name attribute'
    }

}