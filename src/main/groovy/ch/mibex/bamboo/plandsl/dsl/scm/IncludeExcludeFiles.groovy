package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import ch.mibex.bamboo.plandsl.dsl.scm.ScmType.MatchType

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class IncludeExcludeFiles extends BambooObject {
    protected MatchType matchType
    private String filePattern

    IncludeExcludeFiles(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected IncludeExcludeFiles() {}

    /**
     * A regular expression to match the file to be included / excluded.
     */
    void filePattern(String filePattern) {
        this.filePattern = filePattern
    }

}
