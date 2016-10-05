package ch.mibex.bamboo.plandsl.dsl

interface DslScriptParser {
    DslScript parse(DslScriptContext scriptContext, Logger logger, EnvVariableContext envContext)
}
