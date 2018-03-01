package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static ch.mibex.bamboo.plandsl.dsl.Validations.requireNotNullOrEmpty

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

    /**
     * @deprecated use {@link Requirements#equalsTo(String)} instead
     */
    @Deprecated
    static class Equals extends MatchType {
        String matchValue

        // for testing:
        protected Equals() {
        }

        Equals(String matchValue) {
            requireNotNullOrEmpty(matchValue, "Matching value for capability type 'Equals' must not be empty")
            this.matchValue = matchValue
        }
    }

    /**
     * @deprecated use {@link Requirements#matches(String)} instead
     */
    @Deprecated
    static class Matches extends MatchType {
        String regex

        // for testing:
        protected Matches() {
        }

        Matches(String regex) {
            requireNotNullOrEmpty(regex, "Matching regex for capability type 'Matches' must not be empty")
            this.regex = regex
        }
    }

    /**
     * @deprecated use {@link Requirements#exists()} instead
     */
    @Deprecated
    static class Exists extends MatchType {
        Exists() {
        }

        // single-arg ctor necessary for !exists
        protected Exists(String e) {
        }
    }
}
