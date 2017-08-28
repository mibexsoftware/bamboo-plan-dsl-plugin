package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.RequiresBambooVersion
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.4.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class ReleaseVersioning extends BambooObject {
    private String nextReleaseVersion
    private boolean autoIncrement
    private boolean applicableToBranches
    private Set<String> variables = []

    ReleaseVersioning(String nextReleaseVersion, BambooFacade bambooFacade) {
        super(bambooFacade)
        Validations.isNotNullOrEmpty(nextReleaseVersion, 'nextReleaseVersion must be specified')
        this.nextReleaseVersion = nextReleaseVersion
    }

    protected ReleaseVersioning() {}

    /**
     * Automatically increment with each new release?
     */
    void autoIncrement(boolean autoIncrement = true) {
        this.autoIncrement = autoIncrement
    }

    /**
     * Apply the same naming scheme to releases created from branches?
     * If switched off, releases created from branches are named after the branch.
     *
     * @since 1.9.3
     */
    @RequiresBambooVersion(minimumVersion = '6.1.0')
    void applicableToBranches(boolean applicableToBranches = true) {
        this.applicableToBranches = applicableToBranches
    }

    /**
     * Only plan variables and global variables are incremental.
     */
    void variables(String ... variables) {
        this.variables = variables
    }

}
