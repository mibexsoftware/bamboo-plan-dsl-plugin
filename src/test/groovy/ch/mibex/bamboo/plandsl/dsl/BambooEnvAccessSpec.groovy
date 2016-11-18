package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.notifications.HipChatNotification
import spock.lang.Specification


class BambooEnvAccessSpec extends Specification {

    def 'Bamboo environment variable access'() {
        setup:
        def context = new NullBambooFacade() {
            @Override
            BambooEnvironment getVariableContext() {
                new StrictBambooEnvironment(['my.key': 'my.value' , 'my.otherkey': 'my.othervalue'])
            }
        }
        def loader = new DslScriptParserImpl(context)

        when:
        def results = loader.parse(new DslScriptContext(getClass().getResource('/dsls/BambooEnvAccess.groovy').text))
        then:
        def notification = results.projects[0].plans[0].notifications.notifications[0] as HipChatNotification
        notification.apiToken == 'my.othervalue'
        notification.room == 'my.value'
    }
}