package ch.mibex.bamboo.plandsl.dsl.scm.options

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.IncludeExcludeFiles
import ch.mibex.bamboo.plandsl.dsl.scm.ScmType.MatchType
import ch.mibex.bamboo.plandsl.dsl.scm.QuietPeriod
import ch.mibex.bamboo.plandsl.dsl.scm.web.WebRepository
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class AdvancedHgOptions {
    int commandTimeoutInMinutes
    boolean verboseLogs
    QuietPeriod quietPeriod
    IncludeExcludeFiles includeExcludeFiles
    String excludeChangesetsRegex
    WebRepository webRepository

    void commandTimeoutInMinutes(int commandTimeoutInMinutes) {
        this.commandTimeoutInMinutes = commandTimeoutInMinutes
    }

    void verboseLogs(boolean verboseLogs) {
        this.verboseLogs = verboseLogs
    }

    void excludeChangesetsRegex(String excludeChangesetsRegex) {
        this.excludeChangesetsRegex = excludeChangesetsRegex
    }

    void webRepository(@DelegatesTo(WebRepository) Closure closure) {
        webRepository = new WebRepository()
        DslScriptHelper.execute(closure, webRepository)
    }

    void quietPeriod(@DelegatesTo(QuietPeriod) Closure closure) {
        quietPeriod = new QuietPeriod()
        DslScriptHelper.execute(closure, quietPeriod)
    }

    void includeExcludeFiles(MatchType type, @DelegatesTo(IncludeExcludeFiles) Closure closure) {
        includeExcludeFiles = new IncludeExcludeFiles()
        includeExcludeFiles.matchType = type
        DslScriptHelper.execute(closure, includeExcludeFiles)
    }

}
