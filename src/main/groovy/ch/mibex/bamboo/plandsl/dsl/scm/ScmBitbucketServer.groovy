package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedGitRepoOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class ScmBitbucketServer extends ScmType {
    String serverName
    String repositoryUrl
    String projectKey
    String repoSlug
    String repoId
    AdvancedGitRepoOptions advancedOptions
    String branch

    ScmBitbucketServer(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // for tests:
    protected ScmBitbucketServer() {}

    void serverName(String server) {
        this.serverName = server
    }

    void repositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl
    }

    void projectKey(String projectKey) {
        this.projectKey = projectKey
    }

    void repoSlug(String repoSlug) {
        this.repoSlug = repoSlug
    }

    void repoId(String repoId) {
        this.repoId = repoId
    }

    void advancedOptions(@DelegatesTo(AdvancedGitRepoOptions) Closure closure) {
        advancedOptions = new AdvancedGitRepoOptions()
        DslScriptHelper.execute(closure, advancedOptions)
    }

    void branch(String branch) {
        this.branch = branch
    }

}
