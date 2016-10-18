package ch.mibex.bamboo.plandsl.dsl.variables

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class Variable {
    final String key
    final String value

    Variable(String key, String value) {
        this.key = key
        this.value = value
    }
}
