package ch.mibex.bamboo.plandsl.dsl.triggers

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScheduledTrigger extends TriggerType {
    String cronExpression

    void cronExpression(String cronExpression) {
        this.cronExpression = cronExpression
    }

}
