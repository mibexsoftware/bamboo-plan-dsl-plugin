package ch.mibex.bamboo.plandsl.dsl.branches

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
