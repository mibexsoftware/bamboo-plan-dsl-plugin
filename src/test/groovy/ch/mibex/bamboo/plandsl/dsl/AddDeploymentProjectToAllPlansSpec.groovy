package ch.mibex.bamboo.plandsl.dsl

import spock.lang.Specification

class AddDeploymentProjectToAllPlansSpec extends Specification {

    def 'add deployment project to two plans of same project'() {
        setup:
        def loader = new DslScriptParserImpl()

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/AddDeploymentProjectToAllPlans.groovy').text))

        then:
        results.projects[0].plans[0].key == 'ACTIVITIES'
        results.projects[0].plans[0].deploymentProjects[0].name == 'Activity Streams for Bitbucket Server'
        results.projects[1].plans[0].key == 'PLANDSL'
        results.projects[1].plans[0].deploymentProjects[0].name == 'Plan DSL for Bamboo'
    }
}