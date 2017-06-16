package ch.mibex.bamboo.plandsl.dsl

class GroovyIntelliJBug {

    class Bar {
        void b() {
            System.out.println("HURRAI")
        }
    }

    class Foo {
        Bar a(@DelegatesTo(value = Bar, strategy = Closure.DELEGATE_FIRST) Closure closure) {
            def bar = new Bar()
            closure.delegate = bar
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call()
        }
    }

    Foo a(@DelegatesTo(value = Foo, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def foo = new Foo()
        closure.delegate = foo
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.call()
    }

    static void main(String[] args) {
        def bug = new GroovyIntelliJBug()
        bug.a {
            a {
                b()
            }
        }
    }

}
