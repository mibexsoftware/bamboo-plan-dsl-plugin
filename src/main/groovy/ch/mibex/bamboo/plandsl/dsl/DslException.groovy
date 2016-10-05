package ch.mibex.bamboo.plandsl.dsl

class DslException extends RuntimeException {

    DslException(String msg) {
        super(msg)
    }

    DslException(String msg, Exception e) {
        super(msg, e)
    }

}
