package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.IncludeExcludeFiles
import ch.mibex.bamboo.plandsl.dsl.scm.MatchType
import ch.mibex.bamboo.plandsl.dsl.scm.web.WebRepository
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import ch.mibex.bamboo.plandsl.dsl.scm.ScmType.MatchType

@EqualsAndHashCode
@ToString
class AdvancedCvsOptions {
    IncludeExcludeFiles includeExcludeFiles
    String excludeChangesetsRegex
    WebRepository webRepository

    void excludeChangesetsRegex(String excludeChangesetsRegex) {
        this.excludeChangesetsRegex = excludeChangesetsRegex
    }

    void webRepository(@DelegatesTo(WebRepository) Closure closure) {
        webRepository = new WebRepository()
        DslScriptHelper.execute(closure, webRepository)
    }

    void includeExcludeFiles(MatchType type, @DelegatesTo(IncludeExcludeFiles) Closure closure) {
        includeExcludeFiles = new IncludeExcludeFiles()
        includeExcludeFiles.matchType = type
        DslScriptHelper.execute(closure, includeExcludeFiles)
    }

}
