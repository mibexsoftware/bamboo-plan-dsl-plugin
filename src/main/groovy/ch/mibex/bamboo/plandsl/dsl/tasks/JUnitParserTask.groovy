package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString

class JUnitParserTask extends Task {
    static final TASK_ID = 'com.atlassian.bamboo.plugins.testresultparser:task.testresultparser.junit'
    String testResultsDirectory
    boolean pickUpTestResultsCreatedOutsideOfBuild

    JUnitParserTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    void testResultsDirectory(String testResultsDirectory) {
        this.testResultsDirectory = testResultsDirectory
    }

    void pickUpTestResultsCreatedOutsideOfBuild(boolean pickUpTestResultsCreatedOutsideOfBuild = true) {
        this.pickUpTestResultsCreatedOutsideOfBuild = pickUpTestResultsCreatedOutsideOfBuild
    }

    @Override
    def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('testResultsDirectory', testResultsDirectory)
        config.put('pickUpTestResultsCreatedOutsideOfBuild', pickUpTestResultsCreatedOutsideOfBuild)
        config
    }
}
