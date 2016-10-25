package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.NullBambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmType {
    String displayName
    final BambooFacade bambooFacade

    // for tests:
    ScmType() {
        bambooFacade = new NullBambooFacade()
    }

    ScmType(BambooFacade bambooFacade) {
        this.bambooFacade = bambooFacade
    }

    static enum MatchType {
        INCLUDE_ONLY_MATCHING_CHANGES('includeOnly'),
        EXCLUDE_ALL_MATCHING_CHANGES('excludeAll')

        final String type

        private MatchType(String type) {
            this.type = type
        }

        @Override
        String toString() {
            this.type
        }
    }
}
