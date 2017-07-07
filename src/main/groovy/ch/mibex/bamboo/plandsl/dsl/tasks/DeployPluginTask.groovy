package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static ch.mibex.bamboo.plandsl.dsl.Validations.isNotNullOrEmpty

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class DeployPluginTask extends Task {
    private static final TASK_ID = 'com.atlassian.bamboo.plugins.deploy.continuous-plugin-deployment:deploy-task'
    private ProductType productType
    private String deployURL
    private String deployUsername
    private String deployPasswordVariable
    private boolean deployBranchEnabled
    private boolean certificateCheckDisabled
    private boolean useAtlassianIdWebSudo
    private String deployArtifactName

    @Deprecated
    DeployPluginTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    DeployPluginTask(String deployArtifactName, ProductType productType, String deployURL,
                     String deployUsername, String deployPasswordVariable, BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
        this.deployArtifactName = isNotNullOrEmpty(deployArtifactName, 'deployArtifactName must be specified')
        this.productType = isNotNullOrEmpty(productType, 'productType must be specified')
        this.deployURL = isNotNullOrEmpty(deployURL, 'deployURL must be specified')
        this.deployUsername = isNotNullOrEmpty(deployUsername, 'deployUsername must be specified')
        this.deployPasswordVariable =
                isNotNullOrEmpty(deployPasswordVariable, 'deployPasswordVariable must be specified')
    }

    //for tests
    protected DeployPluginTask() {
        super(TASK_ID)
    }

    /**
     * Select a Bamboo artifact to deploy. The artifact should be a single plugin jar file.
     *
     * @deprecated use {@link #DeployPluginTask(String, ProductType, String, String, String, BambooFacade)} instead
     */
    @Deprecated
    void deployArtifactName(String deployArtifactName) {
        this.deployArtifactName = deployArtifactName
    }

    /**
     * The Atlassian product type to deploy the artifact to.
     *
     * @deprecated use {@link #DeployPluginTask(String, ProductType, String, String, String, BambooFacade)} instead
     */
    @Deprecated
    void productType(ProductType productType) {
        this.productType = productType
    }

    /**
     * The address of the remote Atlassian application where the plugin will be installed.
     *
     * @deprecated use {@link #DeployPluginTask(String, ProductType, String, String, String, BambooFacade)} instead
     */
    @Deprecated
    void deployURL(String deployURL) {
        this.deployURL = deployURL
    }

    /**
     * User name to deploy.
     *
     * @deprecated use {@link #DeployPluginTask(String, ProductType, String, String, String, BambooFacade)} instead
     */
    @Deprecated
    void deployUsername(String deployUsername) {
        this.deployUsername = deployUsername
    }

    /**
     * A Bamboo variable with the password for this user to deploy.
     *
     * @deprecated use {@link #DeployPluginTask(String, ProductType, String, String, String, BambooFacade)} instead
     */
    @Deprecated
    void deployPasswordVariable(String deployPasswordVariable) {
        this.deployPasswordVariable = deployPasswordVariable
    }

    /**
     * Allow this task to execute on branch builds. This is disabled by default, since you generally only want a single
     * branch of development being deployed to a single environment.
     */
    void deployBranchEnabled(boolean deployBranchEnabled = true) {
        this.deployBranchEnabled = deployBranchEnabled
    }

    /**
     * Allow SSL verification errors. For example, you may find this useful when deploying to a staging environment
     * that uses a self-signed server certificate.
     */
    void certificateCheckDisabled(boolean certificateCheckDisabled = true) {
        this.certificateCheckDisabled = certificateCheckDisabled
    }

    /**
     * Use Atlassian web sudo.
     */
    void useAtlassianIdWebSudo(boolean useAtlassianIdWebSudo = true) {
        this.useAtlassianIdWebSudo = useAtlassianIdWebSudo
    }

    @Override
    protected Map<String, String> getConfig(Map<Object, Object> context) {
        Map<String, String> config = [:]
        config.put('bcpd.config.productType', productType.productKey)
        config.put('useAtlassianId', 'false')
        config.put('confDeployURL', deployURL)
        config.put('confDeployUsername', deployUsername)
        config.put('enableTrafficLogging', 'false')
        config.put('confDeployPasswordVariableCheck', 'true')
        config.put('confDeployPasswordVariable', deployPasswordVariable)
        config.put('deployBranchEnabled', deployBranchEnabled as String)
        config.put('certificateCheckDisabled', certificateCheckDisabled as String)
        config.put('multiProduct', 'true')
        config.put('atlassianIdPasswordVariableCheck', 'false')
        config.put('useAtlassianIdWebSudo', useAtlassianIdWebSudo as String)
        config.put('confDeployJar', getArtifactId(context, deployArtifactName, true))
        config
    }

    static enum ProductType {
        STASH('bcpd.product.stash'),
        BAMBOO('bcpd.product.bamboo'),
        JIRA('bcpd.product.jira'),
        CONFLUENCE('bcpd.product.confluence'),
        FISHEYE('bcpd.product.fecru')

        ProductType(String name) {
            this.productKey = name
        }

        String productKey
    }

}
