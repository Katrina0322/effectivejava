package com.effective.mvc.annotation;

import java.lang.annotation.*;

/**
 * description:
 * Created by spark on 17-11-27.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value() default "";
}
