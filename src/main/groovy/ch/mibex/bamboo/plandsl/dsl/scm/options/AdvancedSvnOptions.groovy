package ch.mibex.bamboo.plandsl.dsl.scm.options

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.IncludeExcludeFiles
import ch.mibex.bamboo.plandsl.dsl.scm.QuietPeriod
import ch.mibex.bamboo.plandsl.dsl.scm.ScmType.MatchType
import ch.mibex.bamboo.plandsl.dsl.scm.web.WebRepository
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class AdvancedSvnOptions extends BambooObject {
    private boolean detectChangesInExternals
    private boolean useSvnExport
    private boolean enableCommitIsolation
    private boolean autoDetectRootUrlForBranches
    private String branchesRootUrl
    private boolean autoDetectRootUrlForTags
    private String tagRootUrl
    private QuietPeriod quietPeriod
    private IncludeExcludeFiles includeExcludeFiles
    private String excludeChangesetsRegex
    private WebRepository webRepository

    AdvancedSvnOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected AdvancedSvnOptions() {}

    /**
     * Does your repository use svn:externals and you want to detect changes in these paths? This will significantly
     * slow down change detection.
     */
    void detectChangesInExternals(boolean detectChangesInExternals = true) {
        this.detectChangesInExternals = detectChangesInExternals
    }

    /**
     * Using SVN export will speed up first time checkout, but updates are not supported. Implies force clean build.
     */
    void useSvnExport(boolean useSvnExport = true) {
        this.useSvnExport = useSvnExport
    }

    /**
     * Ensures that a build will only have one change, allowing you to isolate your build failures.
     */
    void enableCommitIsolation(boolean enableCommitIsolation = true) {
        this.enableCommitIsolation = enableCommitIsolation
    }

    /**
     * Should VCS branching task automatically determine location of created branches?
     */
    void autoDetectRootUrlForBranches(boolean autoDetectRootUrlForBranches = true) {
        this.autoDetectRootUrlForBranches = autoDetectRootUrlForBranches
    }

    /**
     * Should VCS tagging task automatically determine location of created tags?
     */
    void autoDetectRootUrlForTags(boolean autoDetectRootUrlForTags = true) {
        this.autoDetectRootUrlForTags = autoDetectRootUrlForTags
    }

    /**
     * Location where VCS branching task creates branches.
     */
    void branchesRootUrl(String branchesRootUrl) {
        this.branchesRootUrl = branchesRootUrl
    }

    /**
     * Location where VCS tagging task creates tags.
     */
    void tagRootUrl(String tagRootUrl) {
        this.tagRootUrl = tagRootUrl
    }

    /**
     * A regular expression to match the commit messages to be excluded.
     */
    void excludeChangesetsRegex(String excludeChangesetsRegex) {
        this.excludeChangesetsRegex = excludeChangesetsRegex
    }

    /**
     * Select, if any, the browsable web repository associated with this build.
     */
    void webRepository(@DelegatesTo(value = WebRepository, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        webRepository = new WebRepository(bambooFacade)
        DslScriptHelper.execute(closure, webRepository)
    }

    /**
     * Quiet period allows you to delay building after a single commit is detected, aggregating multiple commits
     * per build.
     */
    void quietPeriod(@DelegatesTo(value = QuietPeriod, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        quietPeriod = new QuietPeriod(bambooFacade)
        DslScriptHelper.execute(closure, quietPeriod)
    }

    /**
     * Customise what files Bamboo uses to detect changes.
     */
    void includeExcludeFiles(MatchType type,
                             @DelegatesTo(value = IncludeExcludeFiles, strategy = Closure.DELEGATE_FIRST) Closure c) {
        includeExcludeFiles = new IncludeExcludeFiles(bambooFacade)
        includeExcludeFiles.matchType = type
        DslScriptHelper.execute(c, includeExcludeFiles)
    }

}
