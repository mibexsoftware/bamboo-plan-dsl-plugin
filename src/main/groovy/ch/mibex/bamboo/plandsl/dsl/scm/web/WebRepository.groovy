package ch.mibex.bamboo.plandsl.dsl.scm.web

import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class WebRepository {
    WebRepositoryType type

    void fisheye(@DelegatesTo(FisheyeWebRepository) Closure closure) {
        type = new FisheyeWebRepository()
        DslScriptHelper.execute(closure, type)
    }

    void stash(@DelegatesTo(StashWebRepository) Closure closure) {
        type = new StashWebRepository()
        DslScriptHelper.execute(closure, type)
    }

    void mercurial(@DelegatesTo(MercurialWebRepository) Closure closure) {
        type = new MercurialWebRepository()
        DslScriptHelper.execute(closure, type)
    }

    void bitbucket(@DelegatesTo(BitbucketWebRepository) Closure closure) {
        type = new BitbucketWebRepository()
        DslScriptHelper.execute(closure, type)
    }
}
