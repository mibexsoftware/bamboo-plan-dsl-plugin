package ch.mibex.bamboo.plandsl.dsl.scm

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import ch.mibex.bamboo.plandsl.dsl.scm.ScmType.MatchType

@EqualsAndHashCode
@ToString
class IncludeExcludeFiles {
    MatchType matchType
    String filePattern

    void filePattern(String filePattern) {
        this.filePattern = filePattern
    }

}
