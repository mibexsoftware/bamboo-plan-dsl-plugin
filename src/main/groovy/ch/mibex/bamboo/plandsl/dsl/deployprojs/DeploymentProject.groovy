package ch.mibex.bamboo.plandsl.dsl.deployprojs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import ch.mibex.bamboo.plandsl.dsl.permissions.Permissions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * A deployment project defines which build plan you get your artifacts from, and contains the environments
 * you want to deploy to.
 *
 * @since 1.1.0
 */
@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class DeploymentProject extends BambooObject {
    private String name
    private Long id
    private String description
    private String useCustomPlanBranch
    private List<Environment> environments = []
    private ReleaseVersioning releaseVersioning
    private Permissions permissions = new Permissions(bambooFacade)

    DeploymentProject(String name, BambooFacade bambooFacade) {
        this(name, null, bambooFacade)
    }

    DeploymentProject(String name, Long id, BambooFacade bambooFacade) {
        super(bambooFacade)
        this.name(name)
        this.id = id
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
     * @param params A collection of properties. Currently "name" and "id" are supported.
     */
    Environment environment(Map<String, Object> params, @DelegatesTo(Environment) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        if (params.containsKey('id')) {
            environment(params['name'] as String, checkEnvironmentId(params), closure)
        } else {
            environment(params['name'] as String, closure)
        }
    }

    private checkEnvironmentId(Map<String, Object> params) {
        Validations.isTrue(params['id'] ==~ /\d+/, 'environment ID must only consist of digits.')
        params['id'] as Long
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
     * @since 1.6.1
     */
    Environment environment(String name, long id, @DelegatesTo(Environment) Closure closure) {
        def env = new Environment(name, id, bambooFacade)
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

    /**
     * Environments represent where releases are deployed to.
     *
     * @param name Name of environment (e.g. Staging, QA, or Production)
     * @param id the ID of the environment
     * @since 1.6.1
     */
    Environment environment(String name, long id) {
        def environment = new Environment(name, id, bambooFacade)
        environments << environment
        environment
    }

    /**
     * Environments represent where releases are deployed to.
     *
     * @param params A collection of properties. Currently only "name" and "id" are supported.
     */
    Environment environment(Map<String, Object> params) {
        if (params.containsKey('id')) {
            environment(params['name'] as String, checkEnvironmentId(params))
        } else {
            environment(params['name'] as String)
        }
    }

    /**
     * Specify what version Bamboo should assign to automatically created releases. You can override this manually
     * whenever you create a new release. Releases from branches will default to using the branch name suffixed
     * with the build number of the build result.
     *
     * @param nextReleaseVersion What version should Bamboo use for the next release? e.g. 1.0-${bamboo.buildNumber}
     */
    ReleaseVersioning releaseVersioning(String nextReleaseVersion, @DelegatesTo(ReleaseVersioning) Closure closure) {
        def releaseVersioning = new ReleaseVersioning(nextReleaseVersion, bambooFacade)
        DslScriptHelper.execute(closure, releaseVersioning)
        this.releaseVersioning = releaseVersioning
        releaseVersioning
    }

    /**
     * Specify what version Bamboo should assign to automatically created releases. You can override this manually
     * whenever you create a new release. Releases from branches will default to using the branch name suffixed
     * with the build number of the build result.
     *
     * @param params A collection of properties. Currently only "nextReleaseVersion" is supported.
     */
    ReleaseVersioning releaseVersioning(Map<String, String> params, @DelegatesTo(ReleaseVersioning) Closure closure) {
        //FIXME this can be improved once https://issues.apache.org/jira/browse/GROOVY-7956 is implemented
        releaseVersioning(params['nextReleaseVersion'], closure)
    }

    /**
     * Specifies the permissions for this deployment project.
     *
     * @since 1.5.1
     */
    Permissions permissions(@DelegatesTo(Permissions) Closure closure) {
        def permissions = new Permissions(bambooFacade)
        DslScriptHelper.execute(closure, permissions)
        this.permissions =  permissions
    }
}
