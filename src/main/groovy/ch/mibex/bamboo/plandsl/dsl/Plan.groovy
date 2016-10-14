package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.Branches
import ch.mibex.bamboo.plandsl.dsl.deployprojs.DeploymentProject
import ch.mibex.bamboo.plandsl.dsl.scm.Scm
import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications
import ch.mibex.bamboo.plandsl.dsl.triggers.Triggers
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Plan extends AbstractBambooElement implements DslParentElement<Stage> {
    String key
    String name
    String description
    Scm scm = new Scm()
    boolean enabled = true
    protected Set<Stage> stages = new LinkedHashSet<>()
    Set<DeploymentProject> deploymentProjects = new LinkedHashSet<>()
    Triggers triggers = new Triggers()
    Branches branches = new Branches()
    Notifications notifications = new Notifications()

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
    void key(String key) {
        bambooFacade.requireSharedCredentials("jdk8")
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
    @Deprecated
    void name(String name) {
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
     * @since 1.1.0
     */
    void deploymentProject(String name, @DelegatesTo(DeploymentProject) Closure closure) {
        def deploymentProject = new DeploymentProject(name)
        DslScriptHelper.execute(closure, deploymentProject)
        deploymentProjects << deploymentProject
        deploymentProject
    }

    /**
     * Specifies the repositories for this plan.
     */
    void scm(@DelegatesTo(Scm) Closure closure) {
        def scm = new Scm()
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
        this.branches = new Branches()
        DslScriptHelper.execute(closure, branches)
    }

    /**
     * Specifies a stage for this plan. If your plan has multiple stages, call this multiple times.
     */
    Stage stage(String name, @DelegatesTo(Stage) Closure closure) {
        Validations.isNotNullOrEmpty(name, 'name must be specified')
        def stage = new Stage(bambooFacade)
        stage.name(name)
        DslScriptHelper.execute(closure, stage)
        stages << stage
        stage
    }

    /**
     * Specifies the notifications for this plan.
     */
    void notifications(@DelegatesTo(Notifications) Closure closure) {
        this.notifications = new Notifications()
        DslScriptHelper.execute(closure, notifications)
    }

    @Override
    Collection<Stage> children() {
        stages
    }
}
