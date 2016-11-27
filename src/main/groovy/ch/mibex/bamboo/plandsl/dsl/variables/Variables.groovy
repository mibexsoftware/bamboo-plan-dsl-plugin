package ch.mibex.bamboo.plandsl.dsl.variables

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Variables extends BambooObject {
    private List<Variable> variables = []

    // for tests
    protected Variables() {}

    protected Variables(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    void variable(String key, String value) {
        Validations.isNotNullOrEmpty(key, 'Variable key must not be empty')
        def variable = new Variable(key, value)
        variables << variable
    }
}
