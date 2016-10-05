package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class DslScriptContextFactorySpec extends Specification {

    def 'Ant pattern for plan dsl lookup'() {
        when:
        def contexts = DslScriptContextFactory.createContexts('jobs/**/*.groovy', false, null, new File('src/test/resources/dsls'))

        then:
        contexts.size() == 4
        contexts[0].body == null
        contexts[0].location.endsWith('ArtifactDefinitions.groovy')
        contexts[1].body == null
        contexts[1].location.endsWith('ArtifactDependency.groovy')
        contexts[2].body == null
        contexts[2].location.endsWith('MultipleJobs.groovy')
        contexts[3].body == null
        contexts[3].location.endsWith('UpdateJobProperties.groovy')
    }

    def 'Ant pattern with no matching files should yield exception'() {
        when:
        DslScriptContextFactory.createContexts('XXX/**/*.groovy', false, null, new File('src/test/resources/dsls'))

        then:
        Exception e = thrown(DslException)
        e.message == 'no DSL scripts found at XXX/**/*.groovy'
    }

    def 'inline body DSL'() {
        when:
        def contexts = DslScriptContextFactory.createContexts(null, true, 'project("KEY") {}', null)

        then:
        contexts.size() == 1
        contexts[0].body == 'project("KEY") {}'
        contexts[0].location == null
    }

}