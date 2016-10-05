package ch.mibex.bamboo.plandsl.dsl.triggers

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class OnceADayTrigger extends TriggerType {
    String buildTime

    void buildTime(String buildTime) {
        this.buildTime = buildTime
    }

}
