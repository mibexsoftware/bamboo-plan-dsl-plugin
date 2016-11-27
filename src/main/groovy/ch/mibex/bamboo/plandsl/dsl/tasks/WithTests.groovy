package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class WithTests extends BambooObject {
    private String testResultsDirectory

    WithTests(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing:
    protected WithTests() {}

    /**
     * Where does the build place generated test results?
     * This is a comma separated list of test result directories. You can also use Ant style patterns such as
     * &#42;&#42;/test-reports/&#42;.xml
     */
    void testResultsDirectory(String testResultsDirectory) {
        this.testResultsDirectory = testResultsDirectory
    }
}
