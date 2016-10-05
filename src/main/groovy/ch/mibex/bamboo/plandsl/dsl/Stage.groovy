package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.jobs.Job

class Stage implements DslParentElement<Job> {
    String name
    String description
    boolean manual
    protected Set<Job> jobs = new LinkedHashSet<>()

    /**
     * How do you want to identify the new plan stage?
     *
     * @param name the name of the stage
     */
    Stage(String name) {
        this.name = name
    }

    protected Stage() {}

    /**
     * Choose a meaningful description for this plan stage.
     */
    void description(String description) {
        Validations.isSafeBambooString(description)
        this.description = description
    }

    /**
     * Specifies if the stage is manual or not. Manual stages require user interaction in order to execute.
     */
    void manual(boolean manual) {
        this.manual = manual
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     */
    Job job(String key, @DelegatesTo(Job) Closure closure) {
        def job = new Job(key)
        DslScriptHelper.execute(closure, job)
        jobs << job
        job
    }

    @Override
    Collection<Job> children() {
        jobs
    }
}
