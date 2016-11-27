package ch.mibex.bamboo.plandsl.dsl;

import java.lang.annotation.*;

/**
 * @since 1.2.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresPlugin {

    String key();

    String minimumVersion() default "";

}

