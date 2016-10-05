package ch.mibex.bamboo.plandsl.dsl

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class DslScriptContext {
    final String location
    final String body
    final URL urlRoot
    final String scriptPath

    DslScriptContext(String body) {
        this(null, body, new File('.').toURI().toURL())
    }

    DslScriptContext(String location, String body, URL urlRoot, String scriptPath = null) {
        if (location && new File(location).name.contains('-')) {
            throw new DslException('Dashes in script filenames are not allowed: ' +
                    "$location. See https://issues.apache.org/jira/browse/GROOVY-505 for more information.")
        }
        this.location = location
        this.body = body
        this.urlRoot = urlRoot
        this.scriptPath = scriptPath
    }

    @Override
    String toString() {
        if (body) {
            "inline script: ${body.take(80)}..."
        } else {
            "script file from ${location}"
        }
    }
}
