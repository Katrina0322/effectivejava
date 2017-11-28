package com.effective.mvc.annotation;

import java.lang.annotation.*;

/**
 * description:
 * Created by spark on 17-11-27.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParams {
    String value() default "";
    boolean require() default true;
}
