package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.notifications.HipChatNotification
import spock.lang.Specification


class BambooEnvAccessSpec extends Specification {

    def 'Bamboo environment variable access'() {
        setup:
        def loader = new DslScriptParserImpl()
        def testLogger = new NullLogger()
        def context = new StrictEnvVariableContext(['my.key': 'my.value'])

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/BambooEnvAccess.groovy').text), testLogger, context)

        then:
        (results.projects[0].plans[0].notifications.notifications[0] as HipChatNotification).room == 'my.value'
    }
}