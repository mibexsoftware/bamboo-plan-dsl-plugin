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
class AdvancedHgOptions extends BambooObject {
    protected int commandTimeoutInMinutes
    protected boolean verboseLogs
    protected QuietPeriod quietPeriod
    protected IncludeExcludeFiles includeExcludeFiles
    protected String excludeChangesetsRegex
    protected WebRepository webRepository

    AdvancedHgOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected AdvancedHgOptions() {}

    /**
     * Specifies how many minutes are given for hg commands to finish. Default is 180 (3 hours).
     */
    void commandTimeoutInMinutes(int commandTimeoutInMinutes) {
        this.commandTimeoutInMinutes = commandTimeoutInMinutes
    }

    /**
     * Outputs more verbose logs from hg commands.
     */
    void verboseLogs(boolean verboseLogs = true) {
        this.verboseLogs = verboseLogs
    }

    /**
     * Customise what files Bamboo uses to detect changes.
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
     * Quiet period allows you to delay building after a single commit is detected, aggregating multiple commits per
     * build.
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
