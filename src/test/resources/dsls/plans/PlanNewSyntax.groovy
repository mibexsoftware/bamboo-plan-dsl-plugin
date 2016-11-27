package dsls.plans


project(name: 'Simple project', key: 'SIMPLEPROJECT') {
    plan(key: 'SIMPLEPLAN', name: 'Simple plan') {
        description "this is a simple plan"
        enabled true
    }
}