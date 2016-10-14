package ch.mibex.bamboo.plandsl.dsl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Documented
public @interface RequiresBambooVersion {

   String minimumVersion();

}