package ch.mibex.bamboo.plandsl.dsl

class NullLogger implements Logger {

    @Override
    void println(String s) {
        //nop
    }

}
