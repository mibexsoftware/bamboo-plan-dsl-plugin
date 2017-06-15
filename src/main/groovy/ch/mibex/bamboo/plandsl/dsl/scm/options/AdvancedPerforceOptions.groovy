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
class AdvancedPerforceOptions extends BambooObject {
    private QuietPeriod quietPeriod
    private IncludeExcludeFiles includeExcludeFiles
    private String excludeChangesetsRegex
    private WebRepository webRepository

    AdvancedPerforceOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected AdvancedPerforceOptions() {}

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
     * Quiet period allows you to delay building after a single commit is detected, aggregating multiple commits per
     * build.
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
