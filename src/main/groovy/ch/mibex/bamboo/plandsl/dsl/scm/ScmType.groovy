package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class ScmType extends BambooObject {
    protected String name

    ScmType(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // for tests:
    ScmType() {}

    static enum MatchType {
        INCLUDE_ONLY_MATCHING_CHANGES('includeOnly'),
        EXCLUDE_ALL_MATCHING_CHANGES('excludeAll')

        final String type

        private MatchType(String type) {
            this.type = type
        }

        @Override
        String toString() { type }
    }
}
