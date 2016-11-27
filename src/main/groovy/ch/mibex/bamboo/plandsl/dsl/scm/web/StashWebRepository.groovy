package ch.mibex.bamboo.plandsl.dsl.scm.web

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class StashWebRepository extends WebRepositoryType {
    private String url
    private String projectKey
    private String repositoryName

    StashWebRepository(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * This is the URL of your Stash instance.
     */
    void url(String url) {
        this.url = url
    }

    /**
     * The key of the project in Stash e.g., PROJ
     */
    void projectKey(String projectKey) {
        this.projectKey = projectKey
    }

    /**
     * The name of the repository in Stash e.g. proj-repo
     */
    void repositoryName(String repositoryName) {
        this.repositoryName = repositoryName
    }
}
