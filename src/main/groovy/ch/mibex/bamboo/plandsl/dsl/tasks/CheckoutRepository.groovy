package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class CheckoutRepository {
    final String name
    String checkoutDirectory

    CheckoutRepository(String name) {
        this.name = name
    }

    void checkoutDirectory(String checkoutDirectory) {
        this.checkoutDirectory = checkoutDirectory
    }

}
