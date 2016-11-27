package ch.mibex.bamboo.plandsl.dsl.scm.web

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class FisheyeWebRepository extends WebRepositoryType {
    private String url
    private String repositoryPath
    private String repositoryName

    FisheyeWebRepository(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected FisheyeWebRepository() {}

    /**
     * This is the URL of your FishEye instance e.g., http://svn.company.com/fisheye/
     */
    void url(String url) {
        this.url = url
    }

    /**
     * The path from the root of the SCM to the FishEye Repository. This makes the difference between a file as
     * defined by the SCM and as seen in FishEye e.g. /company/myProject/trunk/
     */
    void repositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath
    }

    /**
     * The name of the repository in FishEye e.g., myProject
     */
    void repositoryName(String repositoryName) {
        this.repositoryName = repositoryName
    }
}
