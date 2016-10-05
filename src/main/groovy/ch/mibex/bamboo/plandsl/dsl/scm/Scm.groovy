package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.DslParentElement
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Scm implements DslParentElement<ScmType> {
    final Set<ScmType> scms = new LinkedHashSet<>()

    void bitbucketCloud(String displayName, @DelegatesTo(ScmBitbucketCloud) Closure closure) {
        handleScm(closure, displayName, ScmBitbucketCloud)
    }

    void bitbucketServer(String displayName, @DelegatesTo(ScmBitbucketServer) Closure closure) {
        handleScm(closure, displayName, ScmBitbucketServer)
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
        def linkedRepo = new ScmLinkedRepository()
        linkedRepo.displayName = name
        scms << linkedRepo
    }

    private void handleScm(Closure closure, String displayName, Class<? extends ScmType> clazz) {
        def scm = clazz.newInstance()
        scm.displayName = displayName
        DslScriptHelper.execute(closure, scm)
        scms << scm
    }

    @Override
    Collection<ScmType> children() {
         scms
    }
}
