package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class SshAdvancedOptions extends BambooObject {
    private String hostFingerprint
    private int port

    SshAdvancedOptions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected SshAdvancedOptions() {}

    /**
     * Verify remote host fingerprint on connect.
     */
    void hostFingerprint(String fingerprint) {
        hostFingerprint = fingerprint
    }

    /**
     * Port number for remote host ssh connection. Default value is 22.
     */
    void port(int portNr) {
        port = portNr
    }

}
