package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class NCoverOutput extends BambooObject {
    private String nCoverXmlDirectory

    // just for testing
    protected NCoverOutput() {}

    NCoverOutput(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * This is where Bamboo will look for the XML report output file from NCover. The NCover report generation
     * must run as part of your build.
     */
    void nCoverXmlDirectory(String nCoverXmlDirectory) {
        this.nCoverXmlDirectory = nCoverXmlDirectory
    }

}
