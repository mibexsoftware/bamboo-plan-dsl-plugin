package ch.mibex.bamboo.plandsl.dsl.scm

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class QuietPeriod {
    int waitTimeInSeconds
    int maximumRetries

    void waitTimeInSeconds(int waitTimeInSeconds) {
        this.waitTimeInSeconds = waitTimeInSeconds
    }

    void maximumRetries(int maximumRetries) {
        this.maximumRetries = maximumRetries
    }

}
