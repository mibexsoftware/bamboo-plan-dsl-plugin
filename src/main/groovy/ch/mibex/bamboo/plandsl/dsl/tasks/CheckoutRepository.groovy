package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class CheckoutRepository extends BambooObject {
    private String name
    private String checkoutDirectory

    // for testing
    protected CheckoutRepository() {
    }

    CheckoutRepository(String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.name = name
    }

    /**
     * Specify an alternative sub-directory to which the code will be checked out.
     */
    void checkoutDirectory(String checkoutDirectory) {
        this.checkoutDirectory = checkoutDirectory
    }

}
