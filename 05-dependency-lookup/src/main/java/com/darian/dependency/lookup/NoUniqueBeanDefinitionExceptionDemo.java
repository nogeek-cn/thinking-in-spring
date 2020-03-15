package com.darian.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/15  23:56
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        applicationContext.refresh();

        try {
            // 由于 Spring 应用上下文，存在 String 类型的 Bean, 通过单一类型查找会出问题
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("String 应用上下文存在 [ %d ] 个 [ %s ] 类型的 Bean , 具体原因：\n[ %s ]",
                    e.getNumberOfBeansFound(), String.class.getName(), e.getMessage());
        }
        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }

    @Bean
    public String bean3() {
        return "bean3";
    }
}
