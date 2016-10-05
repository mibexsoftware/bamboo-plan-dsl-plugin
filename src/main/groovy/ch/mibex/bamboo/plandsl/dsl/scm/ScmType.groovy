package ch.mibex.bamboo.plandsl.dsl.scm

class ScmType {
    String displayName

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
