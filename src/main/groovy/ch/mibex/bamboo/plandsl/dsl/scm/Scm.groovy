package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class Scm extends ScmType  {
    private List<ScmType> scms = []

    Scm(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * A Bitbucket Cloud repository.
     */
    void bitbucketCloud(String name, @DelegatesTo(ScmBitbucketCloud) Closure closure) {
        handleScm(closure, name, ScmBitbucketCloud)
    }

    /**
     * A Bitbucket Cloud repository.
     */
    void bitbucketCloud(Map<String, String> params, @DelegatesTo(ScmBitbucketCloud) Closure closure) {
        handleScm(closure, params['name'], ScmBitbucketCloud)
    }

    /**
     * A Bitbucket Server repository.
     */
    void bitbucketServer(String name, @DelegatesTo(ScmBitbucketServer) Closure closure) {
        handleScm(closure, name, ScmBitbucketServer)
    }

    /**
     * A Bitbucket Server repository.
     */
    void bitbucketServer(Map<String, String> params, @DelegatesTo(ScmBitbucketServer) Closure closure) {
        handleScm(closure, params['name'], ScmBitbucketServer)
    }

    /**
     * A Git repository.
     */
    void git(String name, @DelegatesTo(ScmGit) Closure closure) {
        handleScm(closure, name, ScmGit)
    }

    /**
     * A Git repository.
     */
    void git(Map<String, String> params, @DelegatesTo(ScmGit) Closure closure) {
        handleScm(closure, params['name'], ScmGit)
    }

    /**
     * A GitHub repository.
     */
    void github(String name, @DelegatesTo(ScmGithub) Closure closure) {
        handleScm(closure, name, ScmGithub)
    }

    /**
     * A GitHub repository.
     */
    void github(Map<String, String> params, @DelegatesTo(ScmGithub) Closure closure) {
        handleScm(closure, params['name'], ScmGithub)
    }

    /**
     * A subversion repository.
     */
    void subversion(String name, @DelegatesTo(ScmSubversion) Closure closure) {
        handleScm(closure, name, ScmSubversion)
    }

    /**
     * A subversion repository.
     */
    void subversion(Map<String, String> params, @DelegatesTo(ScmSubversion) Closure closure) {
        handleScm(closure, params['name'], ScmSubversion)
    }

    /**
     * A Mercurial repository.
     */
    void mercurial(String name, @DelegatesTo(ScmMercurial) Closure closure) {
        handleScm(closure, name, ScmMercurial)
    }

    /**
     * A Mercurial repository.
     */
    void mercurial(Map<String, String> params, @DelegatesTo(ScmMercurial) Closure closure) {
        handleScm(closure, params['name'], ScmMercurial)
    }

    /**
     * A CVS repository.
     */
    void cvs(String name, @DelegatesTo(ScmCvs) Closure closure) {
        handleScm(closure, name, ScmCvs)
    }

    /**
     * A CVS repository.
     */
    void cvs(Map<String, String> params, @DelegatesTo(ScmCvs) Closure closure) {
        handleScm(closure, params['name'], ScmCvs)
    }

    /**
     * A Perforce repository.
     */
    void perforce(String name, @DelegatesTo(ScmPerforce) Closure closure) {
        handleScm(closure, name, ScmPerforce)
    }

    /**
     * A Perforce repository.
     */
    void perforce(Map<String, String> params, @DelegatesTo(ScmPerforce) Closure closure) {
        handleScm(closure, params['name'], ScmPerforce)
    }

    /**
     * A Bamboo linked repository,
     */
    void linkedRepository(String name) {
        bambooFacade.requireLinkedRepository(name)
        def linkedRepo = new ScmLinkedRepository(bambooFacade)
        linkedRepo.name = name
        scms << linkedRepo
    }

    /**
     * A Bamboo linked repository,
     */
    void linkedRepository(Map<String, String> params) {
        bambooFacade.requireLinkedRepository(params['name'])
        def linkedRepo = new ScmLinkedRepository(bambooFacade)
        linkedRepo.name = params['name']
        scms << linkedRepo
    }

    private ScmType handleScm(Closure closure, String displayName, Class<? extends ScmType> clazz) {
        def scm = clazz.newInstance(bambooFacade)
        scm.name = displayName
        DslScriptHelper.execute(closure, scm)
        scms << scm
        scm
    }

}
