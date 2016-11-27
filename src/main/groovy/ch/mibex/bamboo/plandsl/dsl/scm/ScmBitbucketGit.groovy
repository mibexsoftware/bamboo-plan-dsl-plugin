package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedGitRepoOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class ScmBitbucketGit extends ScmType {
    private AdvancedGitRepoOptions advancedOptions

    // for tests:
    protected ScmBitbucketGit() {}

    ScmBitbucketGit(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void advancedOptions(@DelegatesTo(AdvancedGitRepoOptions) Closure closure) {
        def advancedGitOptions = new AdvancedGitRepoOptions(bambooFacade)
        DslScriptHelper.execute(closure, advancedGitOptions)
        this.advancedOptions = advancedGitOptions
    }

}
