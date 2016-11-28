package dsls.scms

//out.println("Creating global repo")
//def gitConfig = chainCreationService.getBuildConfigurationWithDefaults()
//def user = bambooUserManager.getBambooUser("admin")
//out.println("Finished creating global repo.")
//
//repositoryConfigurationService.createGlobalRepository(
//        "myGlobalRepo3",
//        "com.atlassian.bamboo.plugins.atlassian-bamboo-plugin-git:git",
//        null,
//        gitConfig,
//        true,
//        user
//)

project("LINKEDSCM") {
    name "Simple project"

    plan("LINKEDSCM") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            linkedRepository(name: "myGlobalRepo3")
        }
    }
}