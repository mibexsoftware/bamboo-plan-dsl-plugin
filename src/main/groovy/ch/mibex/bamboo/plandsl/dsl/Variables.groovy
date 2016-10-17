package ch.mibex.bamboo.plandsl.dsl

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Variables {
    Set<Variable> variables = new LinkedHashSet<>()

    void variable(String key, String value) {
        Validations.isNotNullOrEmpty(key, 'Variable key must not be empty')
        def variable = new Variable(key, value)
        variables << variable
    }
}
