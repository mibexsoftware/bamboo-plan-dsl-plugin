package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.scm.options.AdvancedGitRepoOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class ScmBitbucketServer extends ScmType {
    private String serverName
    private String repositoryUrl
    private String projectKey
    private String repoSlug
    private String repoId
    private AdvancedGitRepoOptions advancedOptions
    private String branch

    ScmBitbucketServer(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // for tests:
    protected ScmBitbucketServer() {}

    /**
     * Then name of the Bitbucket Server application link. If none is set, the primary link will be used.
     */
    void serverName(String linkName) {
        bambooFacade.requireApplicationLink(linkName)
        this.serverName = linkName
    }

    /**
     * The repository URL.
     */
    void repositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl
    }

    /**
     * The Bitbucket Server project key.
     */
    void projectKey(String projectKey) {
        this.projectKey = projectKey
    }

    /**
     * The Bitbucket Server repository slug.
     */
    void repoSlug(String repoSlug) {
        this.repoSlug = repoSlug
    }

    // since version 1.3.0 of the DSL, this can be determined by the plug-in
    @Deprecated
    void repoId(String repoId) {
        this.repoId = repoId
    }

    void advancedOptions(@DelegatesTo(AdvancedGitRepoOptions) Closure closure) {
        advancedOptions = new AdvancedGitRepoOptions(bambooFacade)
        DslScriptHelper.execute(closure, advancedOptions)
    }

    /**
     * Choose a branch you want to check out your code from.
     */
    void branch(String branch) {
        this.branch = branch
    }

}
