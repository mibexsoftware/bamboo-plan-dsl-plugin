package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class DslScriptContextSpec extends Specification {

    def 'DSL script with dashes in filename should yield exception'() {
        when:
        new DslScriptContext("my-script.groovy", null, null)

        then:
        Exception e = thrown(DslException)
        e.message == 'Dashes in script filenames are not allowed: my-script.groovy. ' +
                'See https://issues.apache.org/jira/browse/GROOVY-505 for more information.'
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
        context.urlRoot == urlRoot
        context.toString() == 'script file from dsl.groovy'
    }

}