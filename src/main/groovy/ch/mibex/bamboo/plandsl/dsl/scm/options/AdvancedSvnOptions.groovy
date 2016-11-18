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
class AdvancedSvnOptions {
    boolean detectChangesInExternals
    boolean useSvnExport
    boolean enableCommitIsolation
    boolean autoDetectRootUrlForBranches
    String branchesRootUrl
    boolean autoDetectRootUrlForTags
    String tagRootUrl

    QuietPeriod quietPeriod
    IncludeExcludeFiles includeExcludeFiles
    String excludeChangesetsRegex
    WebRepository webRepository

    void detectChangesInExternals(boolean detectChangesInExternals = true) {
        this.detectChangesInExternals = detectChangesInExternals
    }

    void useSvnExport(boolean useSvnExport = true) {
        this.useSvnExport = useSvnExport
    }

    void enableCommitIsolation(boolean enableCommitIsolation = true) {
        this.enableCommitIsolation = enableCommitIsolation
    }

    void autoDetectRootUrlForBranches(boolean autoDetectRootUrlForBranches = true) {
        this.autoDetectRootUrlForBranches = autoDetectRootUrlForBranches
    }

    void autoDetectRootUrlForTags(boolean autoDetectRootUrlForTags = true) {
        this.autoDetectRootUrlForTags = autoDetectRootUrlForTags
    }

    void branchesRootUrl(String branchesRootUrl) {
        this.branchesRootUrl = branchesRootUrl
    }

    void tagRootUrl(String tagRootUrl) {
        this.tagRootUrl = tagRootUrl
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
