package org.format.demo.annotation;

import java.lang.annotation.*;

/**
 * Created by maso on 15/2/28.
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface Mapper {
    String value() default "";

}
