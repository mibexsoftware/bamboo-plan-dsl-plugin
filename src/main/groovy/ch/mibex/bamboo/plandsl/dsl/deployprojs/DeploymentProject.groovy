package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.DslParentElement
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Plan
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @since 1.1.0
 */
@ToString
@EqualsAndHashCode
class DeploymentProject implements DslParentElement<Plan> {
    String name
    String description
    String useCustomPlanBranch
    Set<Environment> environments = new LinkedHashSet<>()

    DeploymentProject(String name) {
        Validations.isNotNullOrEmpty(name, 'deployment project name must be specified')
        Validations.isValidBambooEntityName(name, 'deployment project name must not contain special characters.')
        this.name = name
    }

    protected DeploymentProject() {}

    void description(String description) {
        Validations.isSafeBambooString(description)
        this.description = description
    }

    void useCustomPlanBranch(String useCustomPlanBranch) {
        this.useCustomPlanBranch = useCustomPlanBranch
    }

    Environment environment(String name, @DelegatesTo(Environment) Closure closure) {
        def env = new Environment(name)
        DslScriptHelper.execute(closure, env)
        environments << env
        env
    }

    @Override
    Collection<Plan> children() {
        null
    }

}
