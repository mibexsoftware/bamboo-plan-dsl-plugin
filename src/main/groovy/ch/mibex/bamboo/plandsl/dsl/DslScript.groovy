package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.deployprojs.DeploymentProject
import ch.mibex.bamboo.plandsl.dsl.jobs.Job

abstract class DslScript extends Script implements DslFactory {
    final List<Project> projects = []
    BambooFacade bambooFacade

    @Override @Deprecated
    Project project(String key, @DelegatesTo(Project) Closure closure) {
        def project = new Project(key, bambooFacade)
        DslScriptHelper.execute(closure, project)
        projects << project
        project
    }

    @Override
    Project project(String key, String name, @DelegatesTo(Project) Closure closure) {
        def project = new Project(key, name, bambooFacade)
        DslScriptHelper.execute(closure, project)
        projects << project
        project
    }

    @Override
    Project project(Map<String, String> params, @DelegatesTo(Project) Closure closure) {
        project(params['key'], params['name'], closure)
    }

    @Override
    Plan plan(String planKey, String planName) {
        new Plan(planKey, planName, bambooFacade)
    }

    @Override
    Plan plan(Map<String, String> params) {
        plan(params['key'], params['name'])
    }

    @Override
    Stage stage(String name) {
        new Stage(name, bambooFacade)
    }

    @Override
    Stage stage(Map<String, String> params) {
        new Stage(params['name'], bambooFacade)
    }

    @Override
    Job job(String key, String name) {
        new Job(key, name, bambooFacade)
    }

    @Override
    Job job(Map<String, String> params) {
        new Job(params['key'], params['name'], bambooFacade)
    }

    @Override
    DeploymentProject deploymentProject(String name) {
        new DeploymentProject(name, bambooFacade)
    }

    @Override
    DeploymentProject deploymentProject(Map<String, String> params) {
        new DeploymentProject(params['name'], bambooFacade)
    }

    @Override
    BambooEnvironment env() {
        bambooFacade.variableContext
    }

    @Override
    String env(String key) {
        bambooFacade.getVariableContext()(key)
    }

}
