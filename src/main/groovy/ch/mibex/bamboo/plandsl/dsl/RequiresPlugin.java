package ch.mibex.bamboo.plandsl.dsl;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresPlugin {

    String key();

    String minimumVersion() default "";

}

