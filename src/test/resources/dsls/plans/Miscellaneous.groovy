package dsls.plans

project(name: 'Simple project', key: 'SIMPLEPROJECT') {
    plan(key: 'SIMPLEPLAN', name: 'Simple plan') {
        description "this is a simple plan"
        enabled true

        miscellaneous {
            expire {
                expireBuildResults true
                expireBuildLogs true
                expireAfter 10, TimeUnit.WEEKS
                minimumBuildsToKeep 12
                keepBuildsWithTheFollowingLabels "DONOTDELETE", "IMPORTANT"
            }

            configure(
                'custom.ruby-config-runtime': 'SYSTEM 2.0.0-p648@default',
                'custom.ruby-config-environmentVariables': 'SOME_VAR="-D123 -R345"'
            )
        }
    }
}