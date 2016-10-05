package ch.mibex.bamboo.plandsl.dsl.triggers

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class PeriodicTrigger {
    int pollingFrequencyInSecs

    void pollingFrequencyInSecs(int frequency) {
        this.pollingFrequencyInSecs = frequency
    }

}
