package ch.mibex.bamboo.plandsl.dsl.notifications

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class StashLegacyNotification extends NotificationType {
    static final NOTIFICATION_RECIPIENT_TYPE = 'com.atlassian.bamboo.plugins.bamboo-stash-plugin:recipient.stash'

    StashLegacyNotification(String conditionKey, BambooFacade bambooFacade) {
        super(NOTIFICATION_RECIPIENT_TYPE, conditionKey, bambooFacade)
    }

    @Override
    protected Map<String, String[]> getConfig(Map<Object, Object> context) {
        [:]
    }

}
