package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.jobs.Job
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Stage extends BambooObject {
    private String name
    private String description
    private boolean manual
    private List<Job> jobs = []

    protected Stage(String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.name = Validations.requireNotNullOrEmpty(name, 'name must be specified')
    }

    // for testing
    protected Stage() {}

    /**
     * How do you want to identify the new plan stage?
     *
     * @param name the name of the stage
     * @deprecated use {@link #Stage(String, BambooFacade)} instead
     */
    @Deprecated
    protected void name(String name) {
        this.name = Validations.requireNotNullOrEmpty(name, 'name must be specified')
    }

    /**
     * Choose a meaningful description for this plan stage.
     */
    void description(String description) {
        this.description = Validations.requireSafeBambooString(description)
    }

    /**
     * Specifies if the stage is manual or not. Manual stages require user interaction in order to execute.
     */
    void manual(boolean manual = true) {
        this.manual = manual
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     *
     * @param key the key of the job consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters. E. g. CORE (for a module called core)
     * @param name the name of the job
     */
    Job job(String key, String name, @DelegatesTo(value = Job, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def job = new Job(key, name, bambooFacade)
        DslScriptHelper.execute(closure, job)
        jobs << job
        job
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     *
     * @param key the key of the job consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters. E. g. CORE (for a module called core)
     * @param name the name of the job
     */
    Job job(String key, String name) {
        def job = new Job(key, name, bambooFacade)
        jobs << job
        job
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     *
     * @param key the key of the job consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters. E. g. CORE (for a module called core)
     * @deprecated use {@link #job(Map, Closure)} instead
     */
    @Deprecated
    Job job(String key, @DelegatesTo(value = Job, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def job = new Job(key, bambooFacade)
        DslScriptHelper.execute(closure, job)
        jobs << job
        job
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     *
     * @param jobParams the properties for a new job. Currently, "key" and "name" are expected.
     */
    Job job(Map<String, String> jobParams) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        job(jobParams['key'], jobParams['name'])
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     *
     * @param jobParams the properties for a new job. Currently, "key" and "name" are expected.
     */
    Job job(Map<String, String> jobParams,
            @DelegatesTo(value = Job, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        job(jobParams['key'], jobParams['name'], closure)
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     *
     * @param key the key of the job consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters. E. g. CORE (for a module called core)
     * @deprecated use {@link #job(Map, Closure)} instead
     */
    @Deprecated
    Job job(String key) {
        def job = new Job(key, bambooFacade)
        jobs << job
        job
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     *
     * @param job a job object
     */
    Job job(Job job) {
        jobs << job
        job
    }

}
