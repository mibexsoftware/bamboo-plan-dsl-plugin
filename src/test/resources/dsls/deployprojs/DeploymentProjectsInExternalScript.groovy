package dsls.deployprojs

import dsls.deployprojs.commons.MyCommons

def globalDeploymentProject = deploymentProject('global project')

project("SIMPLEPROJECT") {
    name "simple project"

    plan("SIMPLEPLAN") {
        name "simple plan"

        def project1 = deploymentProject('local project')
        MyCommons.addDeploymentProperties(project1)
        MyCommons.addDeploymentProperties(globalDeploymentProject)
        deploymentProject(globalDeploymentProject)
    }
}