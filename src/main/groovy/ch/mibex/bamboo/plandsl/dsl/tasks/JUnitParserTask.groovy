package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class JUnitParserTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.testresultparser:task.testresultparser.junit'
    private String testResultsDirectory
    private boolean pickUpTestResultsCreatedOutsideOfBuild

    JUnitParserTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    /**
     * Where does the build place generated test results?
     * This is a comma separated list of test result directories. You can also use Ant style patterns such as
     * &#42;&#42;/test-reports/&#42;.xml
     */
    void testResultsDirectory(String testResultsDirectory) {
        this.testResultsDirectory = testResultsDirectory
    }

    /**
     * Pick up test results that were created outside of this build. Files created before the current build was started
     * will be analyzed as valid tests results
     */
    void pickUpTestResultsCreatedOutsideOfBuild(boolean pickUpTestResultsCreatedOutsideOfBuild = true) {
        this.pickUpTestResultsCreatedOutsideOfBuild = pickUpTestResultsCreatedOutsideOfBuild
    }

    @Override
    protected def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('testResultsDirectory', testResultsDirectory)
        config.put('pickUpTestResultsCreatedOutsideOfBuild', pickUpTestResultsCreatedOutsideOfBuild)
        config
    }
}
