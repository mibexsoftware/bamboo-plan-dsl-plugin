package dsls.deployprojs.commons

import ch.mibex.bamboo.plandsl.dsl.deployprojs.DeploymentProject

class MyCommons {

    static void addDeploymentProperties(DeploymentProject deploymentproject) {
        deploymentproject.with {
            description "desc1"
            useCustomPlanBranch "release"

            environment("env1") {
                description "desc1"
            }
            environment("env2") {
                description "desc2"
            }
        }
    }

}