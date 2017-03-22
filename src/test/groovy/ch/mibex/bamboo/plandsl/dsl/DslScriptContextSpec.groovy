package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class DslScriptContextSpec extends Specification {

    def 'DSL script with dashes in filename should yield exception'() {
        when:
        new DslScriptContext("my-script.groovy", null, null as URL[])

        then:
        thrown(DslException)
    }

    def 'DSL with inline body text'() {
        when:
        DslScriptContext context = new DslScriptContext('project("PROJECTKEY") {}')

        then:
        context.body == 'project("PROJECTKEY") {}'
        context.toString() == 'inline script: project("PROJECTKEY") {}...'
    }

    def 'DSL with external file'() {
        setup:
        def urlRoot = new File('dsl.groovy').toURI().toURL()

        when:
        DslScriptContext context = new DslScriptContext('dsl.groovy', null, urlRoot)

        then:
        context.location == 'dsl.groovy'
        context.urlRoots == [urlRoot] as URL[]
        context.toString() == 'script file from dsl.groovy'
    }

}