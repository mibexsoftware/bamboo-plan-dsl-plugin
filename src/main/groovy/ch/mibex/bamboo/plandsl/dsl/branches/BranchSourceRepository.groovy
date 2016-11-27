package ch.mibex.bamboo.plandsl.dsl.branches

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true)
@ToString(includeFields=true)
class BranchSourceRepository extends BambooObject {
    private String branchName

    // just for testing
    protected BranchSourceRepository() {}

    BranchSourceRepository(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * The name of the SCM branch.
     *
     * @param branchName The name of the SCM Branch
     */
    void branchName(String branchName) {
        this.branchName = branchName
    }
}
