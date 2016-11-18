package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.deployprojs.DeploymentProject
import ch.mibex.bamboo.plandsl.dsl.jobs.Job

abstract class DslScript extends Script implements DslFactory {
    final Set<Project> projects = new LinkedHashSet<>()
    BambooFacade bambooFacade

    @Override
    Project project(String key, @DelegatesTo(Project) Closure closure) {
        def project = new Project(bambooFacade)
        project.key(key)
        DslScriptHelper.execute(closure, project)
        projects << project
        project
    }

    @Override
    Plan plan(String key) {
        def plan = new Plan(bambooFacade)
        plan.key(key)
        plan
    }

    @Override
    Job job(String key) {
        def job = new Job(bambooFacade)
        job.key(key)
        job
    }

    @Override
    BambooEnvironment env() {
        bambooFacade.variableContext
    }

    @Override
    String env(String key) {
        bambooFacade.getVariableContext()(key)
    }

    @Override
    Stage stage(String name) {
        def stage = new Stage(bambooFacade)
        stage.name(name)
        stage
    }

    @Override
    DeploymentProject deploymentProject(String name) {
        def deploymentProject = new DeploymentProject(bambooFacade)
        deploymentProject.name(name)
        deploymentProject
    }

}
