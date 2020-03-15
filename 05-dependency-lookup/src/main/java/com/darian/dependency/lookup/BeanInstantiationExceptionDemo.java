package com.darian.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/***
 * {@link BeanInstantiationException} 实例
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/16  0:50
 */
public class BeanInstantiationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 BeanDefinition
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(CharSequence.class)
                .getBeanDefinition();
        applicationContext.registerBeanDefinition("errorBean", beanDefinition);

        applicationContext.refresh();

        applicationContext.close();
    }
}
