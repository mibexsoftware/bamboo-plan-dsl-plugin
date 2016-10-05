package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class WithTests {
    String testResultsDirectory

    void testResultsDirectory(String testResultsDirectory) {
        this.testResultsDirectory = testResultsDirectory
    }
}
