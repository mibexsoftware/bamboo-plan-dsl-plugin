package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class ProjectSpec extends Specification {

    def 'project with new syntax should yield correct project'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def result = loader.parse(new DslScriptContext(getClass().getResource('/dsls/projects/ProjectNewSyntax.groovy').text))

        then:
        result.projects.size() == 1
        result.projects[0].key == 'SIMPLEPROJECT'
        result.projects[0].name == 'Simple project'
    }

    def 'project without name should yield exception'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        loader.parse(new DslScriptContext(getClass().getResource('/dsls/projects/InvalidProjectWithoutName.groovy').text))

        then:
        Exception e = thrown(DslScriptException)
        e.message == '(script:5): a project name must be specified'
    }

}