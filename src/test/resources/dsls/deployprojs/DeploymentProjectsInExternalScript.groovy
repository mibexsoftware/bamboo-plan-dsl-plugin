package dsls.deployprojs

import dsls.deployprojs.commons.MyCommons


project("SIMPLEPROJECT") {
    name "simple project"

    plan("SIMPLEPLAN") {
        name "simple plan"

        def project1 = deploymentProject('local project 1')
        MyCommons.addDeploymentProperties(project1)
        def project2 = deploymentProject('local project 2')
        MyCommons.addDeploymentProperties(project2)
    }
}