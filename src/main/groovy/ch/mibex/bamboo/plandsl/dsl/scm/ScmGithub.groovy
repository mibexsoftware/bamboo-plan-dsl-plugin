package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.auth.PasswordAuth
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedGitOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmGithub extends ScmType {
    String repoSlug
    String branch
    PasswordAuth authType
    AdvancedGitOptions advancedOptions

    // for tests:
    protected ScmGithub() {}

    ScmGithub(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void repoSlug(String repoSlug) {
        this.repoSlug = repoSlug
    }

    void branch(String branch) {
        this.branch = branch
    }

    void passwordAuth(@DelegatesTo(PasswordAuth) Closure closure) {
        authType = new PasswordAuth()
        DslScriptHelper.execute(closure, authType)
    }

    void advancedOptions(@DelegatesTo(AdvancedGitOptions) Closure closure) {
        advancedOptions = new AdvancedGitOptions()
        DslScriptHelper.execute(closure, advancedOptions)
    }

}
