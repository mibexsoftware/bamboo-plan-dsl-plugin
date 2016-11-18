package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.Branches
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependencies
import ch.mibex.bamboo.plandsl.dsl.deployprojs.DeploymentProject
import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications
import ch.mibex.bamboo.plandsl.dsl.scm.Scm
import ch.mibex.bamboo.plandsl.dsl.triggers.Triggers
import ch.mibex.bamboo.plandsl.dsl.variables.Variables
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Plan extends BambooObject implements DslParent<Stage> {
    String key
    String name
    String description
    Scm scm = new Scm(bambooFacade)
    boolean enabled = true
    protected Set<Stage> stages = new LinkedHashSet<>()
    Set<DeploymentProject> deploymentProjects = new LinkedHashSet<>()
    Triggers triggers = new Triggers()
    Branches branches = new Branches()
    Notifications notifications = new Notifications()
    Variables variables = new Variables()
    Dependencies dependencies = new Dependencies()

    // for testing
    protected Plan() {}

    protected Plan(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Specifies the key of the plan.
     *
     * @param key the key of the plan consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters
     */
    protected void key(String key) {
        Validations.isNotNullOrEmpty(key, 'plan key must be specified')
        Validations.isTrue(
            key ==~ /[A-Z][A-Z0-9]*/,
            'plan key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
        )
        this.key = key
    }

    /**
     * Specifies the name of the plan.
     */
    void name(String name) {
        Validations.isNotNullOrEmpty(name, 'plan name must be specified')
        this.name = name
    }

    /**
     * Specifies the description of the plan.
     */
    void description(String description) {
        Validations.isSafeBambooString(description)
        this.description = description
    }

    /**
     * Specifies if the plan should be initially enabled or not.
     */
    void enabled(boolean enabled) {
        this.enabled = enabled
    }

    /**
     * Defines a deployment project for this plan. This can be called multiple times if you have multiple deployment
     * projects for this plan.
     *
     * @param name the name of the deployment project
     *
     * @since 1.1.0
     */
    DeploymentProject deploymentProject(String name, @DelegatesTo(DeploymentProject) Closure closure) {
        def deploymentProject = new DeploymentProject(bambooFacade)
        deploymentProject.name(name)
        DslScriptHelper.execute(closure, deploymentProject)
        deploymentProjects << deploymentProject
        deploymentProject
    }

    DeploymentProject deploymentProject(String name) {
        def deploymentProject = new DeploymentProject(bambooFacade)
        deploymentProject.name(name)
        deploymentProjects << deploymentProject
        deploymentProject
    }

    DeploymentProject deploymentProject(DeploymentProject deploymentProject) {
        deploymentProjects << deploymentProject
        deploymentProject
    }

    /**
     * Specifies the repositories for this plan.
     */
    void scm(@DelegatesTo(Scm) Closure closure) {
        def scm = new Scm(bambooFacade)
        DslScriptHelper.execute(closure, scm)
        this.scm = scm
        scm
    }

    /**
     * Specifies the triggers for this plan.
     */
    void triggers(@DelegatesTo(Triggers) Closure closure) {
        this.triggers = new Triggers()
        DslScriptHelper.execute(closure, triggers)
    }

    /**
     * Specifies the branches for this plan.
     */
    void branches(@DelegatesTo(Branches) Closure closure) {
        branches = new Branches()
        DslScriptHelper.execute(closure, branches)
    }

    Branches branches() {
        branches = new Branches()
        branches
    }

    /**
     * Specifies a stage for this plan. If your plan has multiple stages, call this multiple times.
     *
     * @param name the name of the stage
     */
    Stage stage(String name, @DelegatesTo(Stage) Closure closure) {
        Validations.isNotNullOrEmpty(name, 'name must be specified')
        def stage = new Stage(bambooFacade)
        stage.name(name)
        DslScriptHelper.execute(closure, stage)
        stages << stage
        stage
    }

    Stage stage(Stage stage) {
        stages << stage
        stage
    }

    Stage stage(String name) {
        def stage = new Stage(bambooFacade)
        stage.name(name)
        stages << stage
        stage
    }

    /**
     * Specifies the notifications for this plan.
     */
    Notifications notifications(@DelegatesTo(Notifications) Closure closure) {
        notifications = new Notifications()
        DslScriptHelper.execute(closure, notifications)
        notifications
    }

    Notifications notifications() {
        notifications = new Notifications()
        notifications
    }

    /**
     * Specifies the variables for this plan.
     */
    void variables(@DelegatesTo(Variables) Closure closure) {
        def variables = new Variables()
        DslScriptHelper.execute(closure, variables)
        this.variables =  variables
    }

    /**
     * Specifies the dependencies for this plan.
     */
    void dependencies(@DelegatesTo(Dependencies) Closure closure) {
        def dependencies = new Dependencies()
        DslScriptHelper.execute(closure, dependencies)
        this.dependencies =  dependencies
    }

    @Override
    Collection<Stage> children() {
        stages
    }
}
