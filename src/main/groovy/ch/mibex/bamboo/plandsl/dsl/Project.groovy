package ch.mibex.bamboo.plandsl.dsl

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Project extends BambooObject {
    private String projectKey
    private String projectName
    private List<Plan> plans = []

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

    private void projectKey(String key) {
        Validations.isNotNullOrEmpty(key, 'a project key must be specified')
        Validations.isTrue(
                key ==~ /[A-Z0-9]{2,}/,
                'a project key must consist of 2 or more upper case alphanumeric characters'
        )
        this.projectKey = key
    }

    /**
     * Specifies the mandatory name of the project.
     *
     */
    @Deprecated
    void name(String name) {
        projectName(name)
    }

    private void projectName(String name) {
        Validations.isNotNullOrEmpty(name, 'a project name must be specified')
        this.projectName = name
    }

    /**
     * Specifies a plan for this project. If the project has multiple plans, call this multiple times.
     *
     * @param key the key of the plan consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters
     */
    @Deprecated
    Plan plan(String key, @DelegatesTo(Plan) Closure closure) {
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
    Plan plan(Map<String, String> planParams, @DelegatesTo(Plan) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        plan(planParams['key'], planParams['name'], closure)
    }

    /**
     * Specifies a plan for this project. If the project has multiple plans, call this multiple times.
     *
     * @param key the key of the plan consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters
     * @param name the name of the plan
     */
    Plan plan(String key, String name, @DelegatesTo(Plan) Closure closure) {
        def plan = new Plan(key, name, bambooFacade)
        DslScriptHelper.execute(closure, plan)
        plans << plan
        plan
    }

    Plan plan(Plan plan) {
        plans << plan
        plan
    }

}
