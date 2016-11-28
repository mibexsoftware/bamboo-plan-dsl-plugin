package dsls.scms

project("LINKEDSCM") {
    name "Simple project"

    plan("LINKEDSCM") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        scm {
            linkedRepository("myGlobalRepo1")
            linkedRepository(name: "myGlobalRepo2")
        }
    }
}