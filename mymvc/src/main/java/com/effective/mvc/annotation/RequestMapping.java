package com.effective.mvc.annotation;

import java.lang.annotation.*;

/**
 * description:
 * Created by spark on 17-11-27.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
