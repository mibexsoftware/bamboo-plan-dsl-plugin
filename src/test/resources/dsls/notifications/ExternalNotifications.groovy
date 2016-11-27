package dsls.notifications

import dsls.notifications.commons.MyNotifications

project('ATLAS', 'Atlassian plug-ins') {
    plan('PLANDSL', 'Plan DSL for Bamboo Plugin') {
        description 'A Bamboo plug-in to create and maintain your build plans with a Groovy-based DSL'
        enabled true

        def planNotifications = notifications()
        MyNotifications.notifyWithHipChatAndSms(planNotifications, env())
    }
}