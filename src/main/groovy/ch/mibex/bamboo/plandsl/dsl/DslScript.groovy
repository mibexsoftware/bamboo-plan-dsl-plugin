package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.jobs.Job

abstract class DslScript extends Script implements DslFactory {
    final Set<Project> projects = new LinkedHashSet<>()
    BambooFacade bambooFacade

    @Override
    Project project(String key, @DelegatesTo(Project) Closure closure) {
        def project = new Project(bambooFacade)
        project.key(key)
        DslScriptHelper.execute(closure, project)
        project.validate()
        projects << project
        project
    }

    @Override
    Job job(String key, @DelegatesTo(Job) Closure closure) {
        def job = new Job(bambooFacade)
        job.key(key)
        DslScriptHelper.execute(closure, job)
        job.validate()
        job
    }

    @Override
    Job job(String key) {
        def job = new Job(bambooFacade)
        job.key(key)
        job
    }
}
