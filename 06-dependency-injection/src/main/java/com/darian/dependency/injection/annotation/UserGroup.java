package com.darian.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/***
 * 用户组 注解 ， 扩展{@link Qualifier}
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/17  15:41
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface UserGroup {

    @AliasFor(value = "value", annotation = Qualifier.class)
    String value() default "";
}
