package ch.mibex.bamboo.plandsl.dsl.branches

class Variables {
    final Set<Variable> variables = new LinkedHashSet<>()

    void variable(String key, String value) {
        def variable = new Variable(key, value)
        variables << variable
    }

}
