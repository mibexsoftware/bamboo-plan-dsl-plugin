package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslParent
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper

class Scm extends ScmType implements DslParent<ScmType> {
    final List<ScmType> scms = []

    Scm(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void bitbucketCloud(String displayName, @DelegatesTo(ScmBitbucketCloud) Closure closure) {
        handleScm(closure, displayName, ScmBitbucketCloud)
    }

    void bitbucketServer(String displayName, @DelegatesTo(ScmBitbucketServer) Closure closure) {
        ScmBitbucketServer bbsScm = (ScmBitbucketServer) handleScm(closure, displayName, ScmBitbucketServer)
    }

    void git(String displayName, @DelegatesTo(ScmGit) Closure closure) {
        handleScm(closure, displayName, ScmGit)
    }

    void github(String displayName, @DelegatesTo(ScmGithub) Closure closure) {
        handleScm(closure, displayName, ScmGithub)
    }

    void subversion(String displayName, @DelegatesTo(ScmSubversion) Closure closure) {
        handleScm(closure, displayName, ScmSubversion)
    }

    void mercurial(String displayName, @DelegatesTo(ScmMercurial) Closure closure) {
        handleScm(closure, displayName, ScmMercurial)
    }

    void cvs(String displayName, @DelegatesTo(ScmCvs) Closure closure) {
        handleScm(closure, displayName, ScmCvs)
    }

    void perforce(String displayName, @DelegatesTo(ScmPerforce) Closure closure) {
        handleScm(closure, displayName, ScmPerforce)
    }

    void linkedRepository(String name) {
        bambooFacade.requireLinkedRepository(name)
        def linkedRepo = new ScmLinkedRepository(bambooFacade)
        linkedRepo.displayName = name
        scms << linkedRepo
    }

    private ScmType handleScm(Closure closure, String displayName, Class<? extends ScmType> clazz) {
        def scm = clazz.newInstance(bambooFacade)
        scm.displayName = displayName
        DslScriptHelper.execute(closure, scm)
        scms << scm
        scm
    }

    @Override
    Collection<ScmType> children() {
         scms
    }
}
