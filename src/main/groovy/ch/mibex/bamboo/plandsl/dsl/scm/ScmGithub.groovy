package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedGitOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ScmGithub extends ScmType {
    private String repoSlug
    private String branch
    private PasswordAuth authType
    private AdvancedGitOptions advancedOptions

    // for tests:
    protected ScmGithub() {}

    ScmGithub(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Select the repository you want to use for your plan.
     */
    void repoSlug(String repoSlug) {
        this.repoSlug = repoSlug
    }

    /**
     * The name of a branch or a tag that contains the source code.
     */
    void branch(String branch) {
        this.branch = branch
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        authType = new PasswordAuth(bambooFacade)
        DslScriptHelper.execute(closure, authType)
    }

    void advancedOptions(@DelegatesTo(AdvancedGitOptions) Closure closure) {
        advancedOptions = new AdvancedGitOptions(bambooFacade)
        DslScriptHelper.execute(closure, advancedOptions)
    }

}
