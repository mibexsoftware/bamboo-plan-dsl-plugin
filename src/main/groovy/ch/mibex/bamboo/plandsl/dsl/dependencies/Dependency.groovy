package ch.mibex.bamboo.plandsl.dsl.dependencies

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class Dependency {
    final String planKey

    Dependency(String planKey) {
        this.planKey = planKey
    }
}
