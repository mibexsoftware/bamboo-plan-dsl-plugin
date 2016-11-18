package ch.mibex.bamboo.plandsl.dsl

interface BambooEnvironment {

    @Deprecated
    String getAt(String key)

    String call(String key)

}
