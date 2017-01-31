package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Job extends BambooObject {
    private String key
    private String name
    private String description
    private boolean enabled = true
    private Tasks tasksList = new Tasks(bambooFacade)
    private Artifacts artifacts = new Artifacts(bambooFacade)
    private Requirements requirements = new Requirements(bambooFacade)
    private Miscellaneous miscellaneous = new Miscellaneous(bambooFacade)

    protected Job(String key, String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.key(key)
        this.name = Validations.isNotNullOrEmpty(name, 'job name must be specified')
    }

    @Deprecated
    protected Job(String key, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.key(key)
    }

    // for testing:
    protected Job() {}

    /**
     * Creates a job definition.
     *
     * @param key the key of the job consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters. E. g. CORE (for a module called core)
     */
    private void key(String key) {
        Validations.isNotNullOrEmpty(key, 'job key must be specified')
        Validations.isTrue(
                key ==~ /[A-Z][A-Z0-9]*/,
                'job key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
        )
        this.key = key
    }

    /**
     * Specifies the name of the job.
     */
    @Deprecated
    void name(String name) {
        Validations.isNotNullOrEmpty(name, 'job name must be specified')
        this.name = name
    }

    /**
     * Specifies the description of the job.
     */
    void description(String description) {
        Validations.isSafeBambooString(description)
        this.description = description
    }

    /**
     * Specifies if this job should be enabled or not.
     */
    void enabled(boolean enabled = true) {
        this.enabled = enabled
    }

    /**
     * Defines the artifact(s) for this job.
     */
    void artifacts(@DelegatesTo(Artifacts) Closure closure) {
        def artifacts = new Artifacts(bambooFacade)
        DslScriptHelper.execute(closure, artifacts)
        this.artifacts = artifacts
    }

    /**
     * Defines the task(s) for this job.
     */
    void tasks(@DelegatesTo(Tasks) Closure closure) {
        def newTaskList = new Tasks(bambooFacade)
        DslScriptHelper.execute(closure, newTaskList)
        tasksList = newTaskList
    }

    /**
     * Defines the requirements(s) for this job. This job can only be built by agents whose capabilities meet the
     * specified requirements.
     *
     * @since 1.5.0
     */
    void requirements(@DelegatesTo(Requirements) Closure closure) {
        def newRequirements = new Requirements(bambooFacade)
        DslScriptHelper.execute(closure, newRequirements)
        requirements = newRequirements
    }

    /**
     * Defines the miscellaneous settings for this job.
     */
    void miscellaneous(@DelegatesTo(Miscellaneous) Closure closure) {
        def miscellaneous = new Miscellaneous(bambooFacade)
        DslScriptHelper.execute(closure, miscellaneous)
        this.miscellaneous = miscellaneous
    }

}
