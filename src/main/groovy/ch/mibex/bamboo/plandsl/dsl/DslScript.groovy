package ch.mibex.bamboo.plandsl.dsl

abstract class DslScript extends Script implements DslFactory {
    final Set<Project> projects = new LinkedHashSet<>()

    @Override
    Project project(String key, @DelegatesTo(Project) Closure closure) {
        def project = new Project(key)
        DslScriptHelper.execute(closure, project)
        projects << project
        project
    }

}
