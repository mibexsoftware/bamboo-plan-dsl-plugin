package ch.mibex.bamboo.plandsl.dsl


interface BambooFacade {
    void requirePlugin(String pluginKey)

    void requireMinimumPluginVersion(String pluginKey, String minVersion)

    void requireGlobalVariable(String variableName)

    void requireExecutable(String executableName)

    void requirePlan(String planKey)

    void requireJdk(String jdkName)

    void requireLinkedRepository(String repoName)

    void requireBambooVersion(String version)

    void requireSharedCredentials(String name)

    void requireApplicationLink(String name)

    Map<String, Object> getExportedBambooObjects()

    void log(String message)

    Logger getBuildLogger()

    EnvVariableContext getVariableContext()
}
