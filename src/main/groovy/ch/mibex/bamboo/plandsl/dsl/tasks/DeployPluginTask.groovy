package ch.mibex.bamboo.plandsl.dsl.tasks

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class DeployPluginTask extends Task {
    ProductType productType
    String deployURL
    String deployUsername
    String deployPasswordVariable
    boolean deployBranchEnabled
    boolean certificateCheckDisabled
    boolean useAtlassianIdWebSudo
    String deployArtifactName

    static final TASK_ID = 'com.atlassian.bamboo.plugins.deploy.continuous-plugin-deployment:deploy-task'

    DeployPluginTask(BambooFacade bambooFacade) {
        super(bambooFacade, TASK_ID)
    }

    void deployArtifactName(String deployArtifactName) {
        this.deployArtifactName = deployArtifactName
    }

    void productType(ProductType productType) {
        this.productType = productType
    }

    void deployURL(String deployURL) {
        this.deployURL = deployURL
    }

    void deployUsername(String deployUsername) {
        this.deployUsername = deployUsername
    }

    void deployPasswordVariable(String deployPasswordVariable) {
        this.deployPasswordVariable = deployPasswordVariable
    }

    void deployBranchEnabled(boolean deployBranchEnabled) {
        this.deployBranchEnabled = deployBranchEnabled
    }

    void certificateCheckDisabled(boolean certificateCheckDisabled) {
        this.certificateCheckDisabled = certificateCheckDisabled
    }

    void useAtlassianIdWebSudo(boolean useAtlassianIdWebSudo) {
        this.useAtlassianIdWebSudo = useAtlassianIdWebSudo
    }

//    custom('com.atlassian.bamboo.plugins.deploy.continuous-plugin-deployment:deploy-task') {
//        enabled true
//        description 'Deploy plug-in to staging server'
//        configure(
//                'bcpd.config.productType': 'bcpd.product.stash',
//                useAtlassianId: false,
//                confDeployJar: 'v2:23658500:2:0:Activity Streams for Bitbucket Server',
//                confDeployURL: 'https://46.4.34.132/bitbucket',
//                confDeployUsername: 'mrueegg',
//                enableTrafficLogging: false,
//                confDeployPasswordVariableCheck: true,
//                confDeployPasswordVariable: '${bamboo.bitbucket_server_password}',
//                deployBranchEnabled: true,
//                certificateCheckDisabled: true,
//                multiProduct: true,
//                atlassianIdPasswordVariableCheck: false,
//                useAtlassianIdWebSudo: false
//        )
//    }
//}

    @Override
    Map<String, String> getConfig(Map<Object, Object> context) {
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
        def contextArtifacts = context['artifacts']
        def artifact = contextArtifacts[deployArtifactName]
        if (artifact) {
            def info = artifact.asType(ArtifactInfo)
            def artifactKey = "v2:${info.artifactId}:${info.taskId}:${info.transferId}:${info.name}".toString()
            config.put('confDeployJar', artifactKey)
        }
        config
    }

    static enum ProductType {
        STASH('bcpd.product.stash'), BAMBOO('bcpd.product.bamboo')

        ProductType(String name) {
            this.productKey = name
        }

        String productKey
    }

}
