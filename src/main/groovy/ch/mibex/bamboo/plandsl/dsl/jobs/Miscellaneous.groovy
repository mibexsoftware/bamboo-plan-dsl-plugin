package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Miscellaneous extends BambooObject {
    private boolean cleanWorkingDirectoryAfterEachBuild
    private BuildHungOptions buildHungOptions
    private CloverCodeCoverage cloverCodeCoverage
    private NCoverOutput nCoverOutput
    private PatternMatchLabelling patternMatchLabelling

    // just for testing
    protected Miscellaneous() {}

    Miscellaneous(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Removes the source directory after each build. This may increase build times but save disk space.
     */
    void cleanWorkingDirectoryAfterEachBuild(boolean clean = true) {
        cleanWorkingDirectoryAfterEachBuild = clean
    }

    /**
     * When should Bamboo determine a build has hung? Enable and override hanging build criteria for this build.
     * Bamboo uses two measurements to determine if a build has hung, both have to be exceeded:
     * <ul>
     * <li>expected build time - the average build time multiplied by the build time multiplier.</li>
     * <li>log quiet time - the length of time (in minutes) between log entries for a build.</li>
     * </ul>
     */
    void overrideDefaultHangingBuildDetectionCriteria(@DelegatesTo(BuildHungOptions) Closure closure) {
        def buildHungOptions = new BuildHungOptions()
        DslScriptHelper.execute(closure, buildHungOptions)
        this.buildHungOptions = buildHungOptions
    }

    /**
     * NCover is a code coverage tool that helps ensure that your code base is well tested. Where should Bamboo look
     * for the NCover code-coverage output?
     */
    void nCoverOutputWillBeProduced(@DelegatesTo(NCoverOutput) Closure closure) {
        def nCoverOutput = new NCoverOutput()
        DslScriptHelper.execute(closure, nCoverOutput)
        this.nCoverOutput = nCoverOutput
    }

    /**
     * Pattern Match Labelling
     */
    void patternMatchLabelling(@DelegatesTo(PatternMatchLabelling) Closure closure) {
        def patternMatchLabelling = new PatternMatchLabelling()
        DslScriptHelper.execute(closure, patternMatchLabelling)
        this.patternMatchLabelling = patternMatchLabelling
    }

    /**
     * Would you like to view Clover Code Coverage for this plan? Clover is a code coverage tool reports how
     * well tested your code is and also highlights parts of code that require more testing.
     */
    void cloverCodeCoverage(@DelegatesTo(CloverCodeCoverage) Closure closure) {
        def cloverCodeCoverage = new CloverCodeCoverage()
        DslScriptHelper.execute(closure, cloverCodeCoverage)
        this.cloverCodeCoverage = cloverCodeCoverage
    }
}
