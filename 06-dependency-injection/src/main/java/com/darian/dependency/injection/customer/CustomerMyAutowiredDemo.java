package com.darian.dependency.injection.customer;

import com.darian.dependency.injection.annotation.MyAutowired;
import com.darian.dependency.injection.annotation.MyInjected;
import com.darian.domain.User;
import com.darian.domain.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/20  5:16
 */
public class CustomerMyAutowiredDemo {
    @MyAutowired
    public User userMyAutowired;

    @MyInjected
    public User userMyInjected;

    // 如果，你非常需要 实例化，那么就需要 static
    @Bean("myInjectedBeanPostProcessor")
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // 自定义的注解注册替换原有的注解处理，使用新注解 @MyInjected 处理
        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(
                Arrays.asList(MyInjected.class, Autowired.class, Value.class));

        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return beanPostProcessor;
    }

    @Bean("myInjectedBeanPostProcessor1")
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor1() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // 自定义的注解注册替换原有的注解处理，使用新注解 @MyInjected 处理
        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(
                Arrays.asList(MyInjected.class));

        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class ( 配置了类 ) -> Spring Bean


        applicationContext.register(CustomerMyAutowiredDemo.class, UserConfig.class);

        applicationContext.refresh();

        CustomerMyAutowiredDemo demo = applicationContext.getBean(CustomerMyAutowiredDemo.class);

        System.out.println("demo.userMyAutowired: " + demo.userMyAutowired);
        System.out.println("-------------------------------------------------");
        System.out.println("demo.userMyInjected: " + demo.userMyInjected);
        System.out.println("-------------------------------------------------");


        applicationContext.close();

    }


}
