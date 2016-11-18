package ch.mibex.bamboo.plandsl.dsl.variables

import ch.mibex.bamboo.plandsl.dsl.DslParent
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Variables implements DslParent<Variable> {
    Set<Variable> variables = new LinkedHashSet<>()

    void variable(String key, String value) {
        Validations.isNotNullOrEmpty(key, 'Variable key must not be empty')
        def variable = new Variable(key, value)
        variables << variable
    }

    @Override
    Collection<Variable> children() {
        variables
    }
}
