package ch.mibex.bamboo.plandsl.dsl.scm.options

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.IncludeExcludeFiles
import ch.mibex.bamboo.plandsl.dsl.scm.ScmType.MatchType
import ch.mibex.bamboo.plandsl.dsl.scm.QuietPeriod
import ch.mibex.bamboo.plandsl.dsl.scm.web.WebRepository
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class AdvancedGitRepoOptions extends BambooObject {
    protected boolean useShallowClones
    protected boolean useSubmodules
    protected int commandTimeoutInMinutes
    protected boolean verboseLogs
    protected boolean fetchWholeRepository
    protected boolean enableLfsSupport
    protected QuietPeriod quietPeriod
    protected IncludeExcludeFiles includeExcludeFiles
    protected String excludeChangesetsRegex
    protected WebRepository webRepository

    // just for testing:
    protected AdvancedGitRepoOptions() {}

    AdvancedGitRepoOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Fetches the shallowest commit history possible. Do not use if your build depends on full repository history.
     */
    void useShallowClones(boolean useShallowClones = true) {
        this.useShallowClones = useShallowClones
    }

    /**
     * Enable submodules support if defined for repository. If native Git capability is not defined for agent submodules
     * support would be disabled.
     */
    void useSubmodules(boolean useSubmodules = true) {
        this.useSubmodules = useSubmodules
    }

    /**
     * Specifies how many minutes are given for git commands to finish. Default is 180 (3 hours).
     */
    void commandTimeoutInMinutes(int commandTimeoutInMinutes) {
        this.commandTimeoutInMinutes = commandTimeoutInMinutes
    }

    /**
     * Outputs more verbose logs from git commands.
     */
    void verboseLogs(boolean verboseLogs = true) {
        this.verboseLogs = verboseLogs
    }

    /**
     * Fetches whole repository instead of only one branch
     */
    void fetchWholeRepository(boolean fetchWholeRepository = true) {
        this.fetchWholeRepository = fetchWholeRepository
    }

    /**
     * Git Large File Storage (LFS) replaces large files such as audio samples, videos, datasets,
     * and graphics with text pointers inside Git, while storing the file contents on a remote server.
     *
     * @since 1.5.3
     */
    void enableLfsSupport(boolean enableLfsSupport = true) {
        this.enableLfsSupport = enableLfsSupport
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
    void webRepository(@DelegatesTo(WebRepository) Closure closure) {
        webRepository = new WebRepository(bambooFacade)
        DslScriptHelper.execute(closure, webRepository)
    }

    /**
     * Quiet period allows you to delay building after a single commit is detected, aggregating multiple
     * commits per build.
     */
    void quietPeriod(@DelegatesTo(QuietPeriod) Closure closure) {
        quietPeriod = new QuietPeriod(bambooFacade)
        DslScriptHelper.execute(closure, quietPeriod)
    }

    /**
     * Customise what files Bamboo uses to detect changes.
     */
    void includeExcludeFiles(MatchType type, @DelegatesTo(IncludeExcludeFiles) Closure closure) {
        includeExcludeFiles = new IncludeExcludeFiles(bambooFacade)
        includeExcludeFiles.matchType = type
        DslScriptHelper.execute(closure, includeExcludeFiles)
    }

}
