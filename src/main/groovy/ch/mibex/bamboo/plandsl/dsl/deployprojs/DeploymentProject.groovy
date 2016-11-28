package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class DeploymentProject extends BambooObject {
    private String name
    private String description
    private String useCustomPlanBranch
    private List<Environment> environments = []

    DeploymentProject(String name, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.name(name)
    }

    protected DeploymentProject() {}

    private void name(String name) {
        Validations.isNotNullOrEmpty(name, 'deployment project name must be specified')
        Validations.isValidBambooEntityName(name, 'deployment project name must not contain special characters.')
        this.name = name
    }

    /**
     * A description for this deployment plan.
     *
     * @param description A description for this deployment plan
     */
    void description(String description) {
        Validations.isSafeBambooString(description)
        this.description = description
    }

    /**
     * Use a different plan branch than the default one of the plan for this deployment project.
     *
     * @param useCustomPlanBranch Name of custom plan branch
     */
    void useCustomPlanBranch(String useCustomPlanBranch) {
        this.useCustomPlanBranch = useCustomPlanBranch
    }

    /**
     * Environments represent where releases are deployed to.
     *
     * @param params A collection of properties. Currently only "name" is supported.
     */
    Environment environment(Map<String, String> params, @DelegatesTo(Environment) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        environment(params['name'], closure)
    }

    /**
     * Environments represent where releases are deployed to.
     *
     * @param name Name of environment (e.g. Staging, QA, or Production)
     */
    Environment environment(String name, @DelegatesTo(Environment) Closure closure) {
        def env = new Environment(name, bambooFacade)
        DslScriptHelper.execute(closure, env)
        environments << env
        env
    }

    /**
     * Environments represent where releases are deployed to.
     *
     * @param name Name of environment (e.g. Staging, QA, or Production)
     */
    Environment environment(String name) {
        def env = new Environment(name, bambooFacade)
        environments << env
        env
    }

}
