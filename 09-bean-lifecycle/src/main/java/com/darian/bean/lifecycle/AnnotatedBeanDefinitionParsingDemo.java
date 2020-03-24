package com.darian.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/***
 * 注解 BeanDefinition 解析示例
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/25  2:07
 */
public class AnnotatedBeanDefinitionParsingDemo {


    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 Java 注解的 AnnotatedBeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader definitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        // 注册 当前类（非 @Component class）
        definitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

        int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;

        System.out.println("已加载 BeanDefinition 数量：\t" + beanDefinitionCount);
        // 普通的 class 作为 Component 注册到 IOC 容器中，
        AnnotatedBeanDefinitionParsingDemo beanDemo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo",
                AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println("annotatedBeanDefinitionParsingDemo: \t" + beanDemo);

    }
}
