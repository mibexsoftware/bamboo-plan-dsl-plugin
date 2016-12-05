package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class PatternMatchLabelling extends BambooObject {
    private String regexPattern
    private String labels

    // just for testing
    protected PatternMatchLabelling() {}

    PatternMatchLabelling(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * A regular expression describing a pattern that appears in builds to be labelled. Leave blank to label
     * every build.
     */
    void regexPattern(String regexPattern) {
        this.regexPattern = regexPattern
    }

    /**
     * The label(s) to be applied to matching builds. A label of the form "\n" will be substituted with the nth
     * capturing group from the pattern match. You can also include Global or Build Specific variables.
     */
    void labels(String labels) {
        this.labels = labels
    }

}
