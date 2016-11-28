package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class ArtifactDependency extends BambooObject {
    private String name
    private String destinationDirectory

    /**
     * Creates an artifact dependency.
     *
     * @param name the name of the artifact dependency
     */
    ArtifactDependency(String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.name = Validations.isNotNullOrEmpty(name, 'name must not be empty')
    }

    // just for testing
    protected ArtifactDependency() {}

    /**
     * The destination directory for the dependency.
     */
    void destinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory
    }

}
