package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ["metaClass"], callSuper = true)
@ToString(includeFields=true)
class Scm extends ScmType  {
    private final List<ScmType> scms = []

    Scm(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * A Bitbucket Cloud repository.
     */
    void bitbucketCloud(String displayName, @DelegatesTo(ScmBitbucketCloud) Closure closure) {
        handleScm(closure, displayName, ScmBitbucketCloud)
    }

    /**
     * A Bitbucket Server repository.
     */
    void bitbucketServer(String displayName, @DelegatesTo(ScmBitbucketServer) Closure closure) {
        ScmBitbucketServer bbsScm = (ScmBitbucketServer) handleScm(closure, displayName, ScmBitbucketServer)
    }

    /**
     * A Git repository.
     */
    void git(String displayName, @DelegatesTo(ScmGit) Closure closure) {
        handleScm(closure, displayName, ScmGit)
    }

    /**
     * A GitHub repository.
     */
    void github(String displayName, @DelegatesTo(ScmGithub) Closure closure) {
        handleScm(closure, displayName, ScmGithub)
    }

    /**
     * A subversion repository.
     */
    void subversion(String displayName, @DelegatesTo(ScmSubversion) Closure closure) {
        handleScm(closure, displayName, ScmSubversion)
    }

    /**
     * A Mercurial repository.
     */
    void mercurial(String displayName, @DelegatesTo(ScmMercurial) Closure closure) {
        handleScm(closure, displayName, ScmMercurial)
    }

    /**
     * A CVS repository.
     */
    void cvs(String displayName, @DelegatesTo(ScmCvs) Closure closure) {
        handleScm(closure, displayName, ScmCvs)
    }

    /**
     * A Perforce repository.
     */
    void perforce(String displayName, @DelegatesTo(ScmPerforce) Closure closure) {
        handleScm(closure, displayName, ScmPerforce)
    }

    /**
     * A Bamboo linked repository,
     */
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

}
