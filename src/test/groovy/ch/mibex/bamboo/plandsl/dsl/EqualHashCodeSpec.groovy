package ch.mibex.bamboo.plandsl.dsl

import groovy.transform.EqualsAndHashCode
import spock.lang.Specification

@EqualsAndHashCode(includeFields=true)
class Person {
    private String name
    Person(String name) {
        this.name = name
    }
}
@EqualsAndHashCode(includeFields=true, callSuper = true)
class Developer extends Person {
    private String favouriteProgLang

    Developer(String name, String favouriteProgLang) {
        super(name)
        this.favouriteProgLang = favouriteProgLang
    }
}



class EqualHashCodeSpec extends Specification {

    def 'my test'() {
        setup:

        when:
        def d1 = new Developer("Peter", "C++")
        def d2 =  new Developer("Peter", "C++")

        then:
        d1 == d2
    }
}
