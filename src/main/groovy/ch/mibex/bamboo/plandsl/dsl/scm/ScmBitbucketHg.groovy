package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedHgBitbucketOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ScmBitbucketHg extends ScmType {
    private AdvancedHgBitbucketOptions advancedOptions

    // for tests:
    protected ScmBitbucketHg() {}

    ScmBitbucketHg(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void advancedOptions(@DelegatesTo(AdvancedHgBitbucketOptions) Closure closure) {
        def advancedOptions = new AdvancedHgBitbucketOptions(bambooFacade)
        DslScriptHelper.execute(closure, advancedOptions)
        this.advancedOptions = advancedOptions
    }

}
