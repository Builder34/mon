package com.builder.common.core.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 * @author Builder34
 * @email lcbiao34@gmail.com
 * @date 2018-07-16 22:22:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
