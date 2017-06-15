package ch.mibex.bamboo.plandsl.dsl.scm.web

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class WebRepository extends BambooObject {
    private WebRepositoryType type

    WebRepository(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected WebRepository() {}

    /**
     * Fisheye web repository.
     */
    void fisheye(@DelegatesTo(value = FisheyeWebRepository, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        type = new FisheyeWebRepository(bambooFacade)
        DslScriptHelper.execute(closure, type)
    }

    /**
     * Stash (legacy) web repository.
     */
    void stash(@DelegatesTo(value = StashWebRepository, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        type = new StashWebRepository(bambooFacade)
        DslScriptHelper.execute(closure, type)
    }

    /**
     * Mercurial web repository.
     */
    void mercurial(@DelegatesTo(value = MercurialWebRepository, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        type = new MercurialWebRepository(bambooFacade)
        DslScriptHelper.execute(closure, type)
    }

    /**
     * Bitbucket web repository.
     */
    void bitbucket(@DelegatesTo(value = BitbucketWebRepository, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        type = new BitbucketWebRepository(bambooFacade)
        DslScriptHelper.execute(closure, type)
    }
}
