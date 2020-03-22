package com.darian.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/***
 * 自定义注解实现
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/20  5:10
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {

    @AliasFor(value = "required", annotation = Autowired.class)
    boolean required() default true;
}
