package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification

class HerokuDeployWarTaskSpec extends Specification {

    def 'heroku deploy WAR task'() {
        setup:
        def loader = new DslScriptParserImpl()
        def dsl = getClass().getResource('/dsls/tasks/HerokuDeployWar.groovy').text

        when:
        def results = loader.parse(new DslScriptContext(dsl))

        then:
        results.projects[0].plans[0].stages[0].jobs[0].tasksList.tasks[0] == new HerokuDeployWarTask(
                enabled: true,
                apiKey: 'key',
                appName: 'myapp',
                warFile: 'my.war'
        )
    }

}
