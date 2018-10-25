package com.builder.common.utils.annotation;

import java.lang.annotation.*;

/**
 * api接口，忽略Token验证
 * @author builder34
 * @email lcbiao34@gmail.com
 * @date 2017-03-23 15:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
