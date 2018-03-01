package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Project extends BambooObject {
    private String key
    private String name
    private List<Plan> plans = []
    private Permissions projectPermissions = new Permissions(bambooFacade)
    private Permissions planPermissions = new Permissions(bambooFacade)

    /**
     * @deprecated use {@link #Project(String, String, BambooFacade)} instead
     */
    @Deprecated
    protected Project(String key, BambooFacade bambooFacade) {
        super(bambooFacade)
        projectKey(key)
    }

    protected Project(String key, String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        projectKey(key)
        projectName(name)
    }

    protected Project() { // necessary for testing
    }

    private void projectKey(String key) {
        Validations.requireNotNullOrEmpty(key, 'a project key must be specified')
        Validations.requireTrue(
                key ==~ /[A-Z0-9]{2,}/,
                'a project key must consist of 2 or more upper case alphanumeric characters'
        )
        this.key = key
    }

    /**
     * Specifies the mandatory name of the project.
     *
     * @deprecated use {@link #Project(String, String, BambooFacade)} instead
     */
    @Deprecated
    void name(String name) {
        projectName(name)
    }

    private void projectName(String name) {
        Validations.requireNotNullOrEmpty(name, 'a project name must be specified')
        this.name = name
    }

    /**
     * Specifies a plan for this project. If the project has multiple plans, call this multiple times.
     *
     * @param key the key of the plan consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters
     * @deprecated use {@link #plan(Map, Closure)} instead
     */
    @Deprecated
    Plan plan(String key, @DelegatesTo(value = Plan, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def plan = new Plan(key, bambooFacade)
        DslScriptHelper.execute(closure, plan)
        plans << plan
        plan
    }

    /**
     * Specifies a plan for this project. If the project has multiple plans, call this multiple times.
     *
     * @param key the key of the plan consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters
     */
    Plan plan(Map<String, String> planParams,
              @DelegatesTo(value = Plan, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        plan(planParams['key'], planParams['name'], closure)
    }

    /**
     * Specifies a plan for this project. If the project has multiple plans, call this multiple times.
     *
     * @param key the key of the plan consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters
     */
    Plan plan(Map<String, String> planParams) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        plan(planParams['key'], planParams['name'])
    }

    /**
     * Specifies a plan for this project. If the project has multiple plans, call this multiple times.
     *
     * @param key the key of the plan consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters
     * @param name the name of the plan
     */
    Plan plan(String key, String name, @DelegatesTo(value = Plan, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def plan = new Plan(key, name, bambooFacade)
        DslScriptHelper.execute(closure, plan)
        plans << plan
        plan
    }

    /**
     * Specifies a plan for this project. If the project has multiple plans, call this multiple times.
     *
     * @param key the key of the plan consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters
     * @param name the name of the plan
     */
    Plan plan(String key, String name) {
        def plan = new Plan(key, name, bambooFacade)
        plans << plan
        plan
    }

    Plan plan(Plan plan) {
        plans << plan
        plan
    }

    /**
     * Specifies the permissions for this project.
     *
     * @since 1.9.7
     */
    @RequiresBambooVersion(minimumVersion = '6.2.0')
    Permissions projectPermissions(
            @DelegatesTo(value = Permissions, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def permissions = new Permissions(bambooFacade)
        DslScriptHelper.execute(closure, permissions)
        this.projectPermissions = permissions
    }

    /**
     * Specifies the plan permissions for this project.
     *
     * @since 1.9.7
     */
    @RequiresBambooVersion(minimumVersion = '6.2.0')
    Permissions planPermissions(@DelegatesTo(value = Permissions, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def permissions = new Permissions(bambooFacade)
        DslScriptHelper.execute(closure, permissions)
        this.planPermissions = permissions
    }
}
