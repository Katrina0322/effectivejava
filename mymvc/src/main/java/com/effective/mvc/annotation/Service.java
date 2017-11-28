package com.effective.mvc.annotation;

import java.lang.annotation.*;

/**
 * description:
 * Created by spark on 17-11-27.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
