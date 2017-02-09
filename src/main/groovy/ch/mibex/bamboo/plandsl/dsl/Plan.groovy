package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.branches.Branches
import ch.mibex.bamboo.plandsl.dsl.dependencies.Dependencies
import ch.mibex.bamboo.plandsl.dsl.deployprojs.DeploymentProject
import ch.mibex.bamboo.plandsl.dsl.notifications.Notifications
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import ch.mibex.bamboo.plandsl.dsl.scm.Scm
import ch.mibex.bamboo.plandsl.dsl.triggers.Triggers
import ch.mibex.bamboo.plandsl.dsl.variables.Variables
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Plan extends BambooObject {
    private String key
    private String name
    private String description
    private Scm scm = new Scm(bambooFacade)
    private boolean enabled = true
    private List<Stage> stages = []
    private List<DeploymentProject> deploymentProjects = []
    private Triggers triggers = new Triggers(bambooFacade)
    private Branches branches = new Branches(bambooFacade)
    private Notifications notifications = new Notifications(bambooFacade)
    private Variables variables = new Variables(bambooFacade)
    private Dependencies dependencies = new Dependencies(bambooFacade)
    private Permissions permissions = new Permissions(bambooFacade)

    // for testing
    protected Plan() {}

    @Deprecated
    protected Plan(String key, BambooFacade bambooFacade) {
        super(bambooFacade)
        planKey(key)
    }

    /**
     * Specifies the key of the plan.
     *
     * @param key the key of the plan consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters
     * @param name the name of the build plan
     */
    protected Plan(String key, String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        planKey(key)
        planName(name)
    }

    private void planKey(String key) {
        Validations.isNotNullOrEmpty(key, 'plan key must be specified')
        Validations.isTrue(
                key ==~ /[A-Z][A-Z0-9]*/,
                'key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
        )
        this.key = key
    }

    /**
     * Specifies the name of the plan.
     */
    @Deprecated
    void name(String name) {
        planName(name)
    }

    private void planName(String name) {
        Validations.isNotNullOrEmpty(name, 'plan name must be specified')
        this.name = name
    }

    /**
     * Specifies the description of the plan.
     */
    void description(String description) {
        this.description = Validations.isSafeBambooString(description)
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
        def deploymentProject = new DeploymentProject(name, bambooFacade)
        DslScriptHelper.execute(closure, deploymentProject)
        deploymentProjects << deploymentProject
        deploymentProject
    }

    /**
     * Defines a deployment project for this plan. This can be called multiple times if you have multiple deployment
     * projects for this plan.
     *
     * @param params the parameters of the deployment project. Only "name" is expected.
     * @since 1.1.0
     */
    DeploymentProject deploymentProject(Map<String, String> params, @DelegatesTo(DeploymentProject) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        deploymentProject(params['name'], closure)
    }

    /**
     * Defines a deployment project for this plan. This can be called multiple times if you have multiple deployment
     * projects for this plan.
     *
     * @param params the parameters of the deployment project. Only "name" is expected
     * @since 1.1.0
     */
    DeploymentProject deploymentProject(Map<String, String> params) {
        deploymentProject(params['name'])
    }

    /**
     * Defines a deployment project for this plan. This can be called multiple times if you have multiple deployment
     * projects for this plan.
     *
     * @param name the name of the deployment project
     *
     * @since 1.1.0
     */
    DeploymentProject deploymentProject(String name) {
        def deploymentProject = new DeploymentProject(name, bambooFacade)
        deploymentProjects << deploymentProject
        deploymentProject
    }

    /**
     * Defines a deployment project for this plan. This can be called multiple times if you have multiple deployment
     * projects for this plan.
     *
     * @param name the name of the deployment project
     *
     * @since 1.1.0
     */
    DeploymentProject deploymentProject(DeploymentProject deploymentProject) {
        deploymentProjects << deploymentProject
        deploymentProject
    }

    /**
     * Specifies the repositories for this plan.
     */
    Scm scm(@DelegatesTo(Scm) Closure closure) {
        Scm scm = new Scm(bambooFacade)
        DslScriptHelper.execute(closure, scm)
        this.scm = scm
    }

    /**
     * Specifies the triggers for this plan.
     */
    Triggers triggers(@DelegatesTo(Triggers) Closure closure) {
        Triggers triggers = new Triggers(bambooFacade)
        DslScriptHelper.execute(closure, triggers)
        this.triggers = triggers
    }

    /**
     * Specifies the branches for this plan.
     */
    Branches branches(@DelegatesTo(Branches) Closure closure) {
        Branches branches = new Branches(bambooFacade)
        DslScriptHelper.execute(closure, branches)
        this.branches = branches
    }

    Branches branches() {
        branches = new Branches(bambooFacade)
        branches
    }

    /**
     * Specifies a stage for this plan. If your plan has multiple stages, call this multiple times.
     *
     * @param name the name of the stage
     */
    Stage stage(String name, @DelegatesTo(Stage) Closure closure) {
        def stage = new Stage(name, bambooFacade)
        DslScriptHelper.execute(closure, stage)
        stages << stage
        stage
    }

    /**
     * Specifies a stage for this plan. If your plan has multiple stages, call this multiple times.
     *
     * @param stageParams the properties for the stage. Currently, only "name" is expected.
     */
    Stage stage(Map<String, String> stageParams, @DelegatesTo(Stage) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        stage(stageParams['name'], closure)
    }

    /**
     * Specifies a stage for this plan. If your plan has multiple stages, call this multiple times.
     *
     * @param stageParams the properties for the stage. Currently, only "name" is expected.
     */
    Stage stage(Map<String, String> stageParams) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        stage(stageParams['name'])
    }

    Stage stage(Stage stage) {
        stages << stage
        stage
    }

    /**
     * Specifies a stage for this plan. If your plan has multiple stages, call this multiple times.
     *
     * @param name the name for the stage
     */
    Stage stage(String name) {
        def stage = new Stage(name, bambooFacade)
        stages << stage
        stage
    }

    /**
     * Specifies the notifications for this plan.
     */
    Notifications notifications(@DelegatesTo(Notifications) Closure closure) {
        notifications = new Notifications(bambooFacade)
        DslScriptHelper.execute(closure, notifications)
        notifications
    }

    Notifications notifications() {
        notifications = new Notifications(bambooFacade)
        notifications
    }

    /**
     * Specifies the variables for this plan.
     */
    Variables variables(@DelegatesTo(Variables) Closure closure) {
        def variables = new Variables(bambooFacade)
        DslScriptHelper.execute(closure, variables)
        this.variables = variables
    }

    /**
     * Specifies the dependencies for this plan.
     */
    Dependencies dependencies(@DelegatesTo(Dependencies) Closure closure) {
        def dependencies = new Dependencies(bambooFacade)
        DslScriptHelper.execute(closure, dependencies)
        this.dependencies = dependencies
    }

    /**
     * Specifies the permissions for this plan.
     *
     * @since 1.5.1
     */
    Permissions permissions(@DelegatesTo(Permissions) Closure closure) {
        def permissions = new Permissions(bambooFacade)
        DslScriptHelper.execute(closure, permissions)
        this.permissions = permissions
    }
}
