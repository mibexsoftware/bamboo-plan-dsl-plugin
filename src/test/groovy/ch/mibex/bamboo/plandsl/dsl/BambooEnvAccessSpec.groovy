package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.notifications.HipChatNotification
import spock.lang.Specification


class BambooEnvAccessSpec extends Specification {

    def 'Bamboo environment variable access'() {
        setup:
        def context = new NullBambooFacade() {
            @Override
            EnvVariableContext getVariableContext() {
                new StrictEnvVariableContext(['my.key': 'my.value'])
            }
        }
        def loader = new DslScriptParserImpl(context)

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/BambooEnvAccess.groovy').text))

        then:
        (results.projects[0].plans[0].notifications.notifications[0] as HipChatNotification).room == 'my.value'
    }
}