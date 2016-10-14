package ch.mibex.bamboo.plandsl.dsl

class Project extends AbstractBambooElement implements DslParentElement<Plan> {
    String key
    String name
    final Set<Plan> plans = new LinkedHashSet<>()

    protected Project(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    /**
     * Specifies the project key.
     *
     * @param key the key of the project consisting of 2 or more upper case alphanumeric characters
     */
    void key(String key) {
        Validations.isNotNullOrEmpty(key, 'a project key must be specified')
        Validations.isTrue(
            key ==~ /[A-Z0-9]{2,}/,
            'a project key must consist of 2 or more upper case alphanumeric characters'
        )
        this.key = key
    }

    /**
     * Specifies the name of the project.
     */
    void name(String name) {
        Validations.isNotNullOrEmpty(key, 'a project name must be specified')
        this.name = name
    }

    /**
     * Specifies a plan for this project. If the project has multiple plans, call this multiple times.
     */
    Plan plan(String key, @DelegatesTo(Plan) Closure closure) {
        def plan = new Plan(bambooFacade)
        plan.key(key)
        DslScriptHelper.execute(closure, plan)
        plans << plan
        plan
    }

    @Override
    Collection<Plan> children() {
        plans
    }

}
