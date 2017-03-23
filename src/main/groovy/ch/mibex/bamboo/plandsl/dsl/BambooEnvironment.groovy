package ch.mibex.bamboo.plandsl.dsl

interface BambooEnvironment {

    /**
     * @deprecated use {@link #call(String)} instead
     */
    @Deprecated
    String getAt(String key)

    String call(String key)

}
