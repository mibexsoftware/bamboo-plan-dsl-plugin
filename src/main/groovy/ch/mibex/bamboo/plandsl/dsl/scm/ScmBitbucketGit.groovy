package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedGitRepoOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmBitbucketGit extends ScmType {
    AdvancedGitRepoOptions advancedOptions

    // for tests:
    protected ScmBitbucketGit() {}

    ScmBitbucketGit(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void advancedOptions(@DelegatesTo(AdvancedGitRepoOptions) Closure closure) {
        def advancedGitOptions = new AdvancedGitRepoOptions()
        DslScriptHelper.execute(closure, advancedGitOptions)
        this.advancedOptions = advancedGitOptions
    }

}
