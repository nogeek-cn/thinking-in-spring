package com.darian.dependency.injection.annotation;

import java.lang.annotation.*;

/***
 * 自定义的依赖注入注解
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/20  5:26
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyInjected {

}
