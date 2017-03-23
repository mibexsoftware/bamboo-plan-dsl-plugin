package ch.mibex.bamboo.plandsl.dsl

abstract class DslScript extends Script implements DslFactory {
    protected final List<Project> projects = []
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

}
