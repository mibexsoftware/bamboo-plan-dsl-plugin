package ch.mibex.bamboo.plandsl.dsl.triggers

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class OnceADayTrigger extends TriggerType {
    private String buildTime

    // for tests
    protected OnceADayTrigger() {}

    protected OnceADayTrigger(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * At what time should Bamboo build? use hh:mm e.g. 17:30.
     */
    void buildTime(String buildTime) {
        this.buildTime = buildTime
    }

}
