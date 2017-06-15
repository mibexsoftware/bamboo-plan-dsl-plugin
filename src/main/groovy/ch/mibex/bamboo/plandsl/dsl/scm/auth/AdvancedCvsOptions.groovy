package ch.mibex.bamboo.plandsl.dsl.scm.auth

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.IncludeExcludeFiles
import ch.mibex.bamboo.plandsl.dsl.scm.ScmType.MatchType
import ch.mibex.bamboo.plandsl.dsl.scm.web.WebRepository
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class AdvancedCvsOptions extends BambooObject {
    private IncludeExcludeFiles includeExcludeFiles
    private String excludeChangesetsRegex
    private WebRepository webRepository

    AdvancedCvsOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected AdvancedCvsOptions() {}

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
     * Customise what files Bamboo uses to detect changes.
     */
    void includeExcludeFiles(MatchType type,
                             @DelegatesTo(value = IncludeExcludeFiles, strategy = Closure.DELEGATE_FIRST) Closure c) {
        includeExcludeFiles = new IncludeExcludeFiles(bambooFacade)
        includeExcludeFiles.matchType = type
        DslScriptHelper.execute(c, includeExcludeFiles)
    }

}
