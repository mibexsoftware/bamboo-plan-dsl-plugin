package ch.mibex.bamboo.plandsl.dsl.variables

import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class Variable {
    final String key
    final String value

    Variable(String key, String value) {
        Validations.isNotNullOrEmpty(key, 'key must be specified')
        this.key = key
        this.value = value
    }
}
