package ch.mibex.bamboo.plandsl.dsl.branches

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class BranchSourceRepository {
    String branchName

    void branchName(String branchName) {
        this.branchName = branchName
    }
}
