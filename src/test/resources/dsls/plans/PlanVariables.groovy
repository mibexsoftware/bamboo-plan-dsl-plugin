package dsls.plans

project("SIMPLEPROJECT") {
    name "Simple project"

    plan("SIMPLEPLAN") {
        name "Simple plan"
        description "this is a simple plan"
        enabled true

        variables {
            variable "key1", "value1"
            variable "key2", "value2"
        }
    }
}