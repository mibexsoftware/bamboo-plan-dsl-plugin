package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Requirement extends BambooObject {
    private String capabilityKey
    private MatchType matchType

    /**
     * Creates a capability requirement.
     *
     * @param capabilityKey the key of the required capability, e.g. "system.builder.gradle.Gradle 2.2"
     * @param matchType the match type: "Equals", "Exists" or "Matches"
     */
    Requirement(String capabilityKey, MatchType matchType, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.capabilityKey = capabilityKey
        this.matchType = matchType
    }

    // just for testing
    protected Requirement() {}

    @EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
    @ToString(includeFields=true)
    static abstract class MatchType {}

    static class Equals extends MatchType {
        final String matchValue

        Equals(String matchValue) {
            this.matchValue = matchValue
        }
    }

    static class Matches extends MatchType {
        final String regex

        /**
         * @param regex A regular expression describing a pattern that the key must match.
         */
        Matches(String regex) {
            this.regex = regex
        }
    }

    static class Exists extends MatchType {
    }
}
