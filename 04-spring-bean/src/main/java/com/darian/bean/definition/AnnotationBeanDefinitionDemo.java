package com.darian.bean.definition;

import com.darian.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/***
 * 注解 BeanDefinition 示例
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/14  10:56
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class (配置类)
        applicationContext.register(Config.class);
        // 1. 通过 @Bean 方法定义
        // 2. 通过 @Component 方式
        // 3. 通过 @Import 来进行导入


        // 通过 BeanDefinition 注册 API 实现
        // 1. 命名 Bean 的注册方式
        registerBeanDefinition(applicationContext, "darian-beandefinition", User.class);
        // 2. 非命名 Bean 的注册方式
        registerBeanDefinition(applicationContext, User.class);

        applicationContext.refresh();

        // 会不会重复注册？？？(@Compentent 和 @Import)
        // 按照类型进行依赖查找
        System.out.println("config 的所有 Beans :" + applicationContext.getBeansOfType(Config.class));
        System.out.println("user 的所有 Beans :" + applicationContext.getBeansOfType(User.class));


        // 关闭 Spring 上下文
        applicationContext.close();
    }

    /**
     * 命名 Bean 的注册方式
     *
     * @param registry
     * @param beanName
     * @param beanClass
     * @param <T>
     */
    public static <T> void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName,
                                                  Class<T> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id", 1L)
                .addPropertyValue("name", "darian");

        if (StringUtils.hasText(beanName)) {
            // 注册 BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名的方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static <T> void registerBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry,
                                                  Class<T> beanClass) {
        registerBeanDefinition(beanDefinitionRegistry, null, User.class);
    }

    @Component // 定义当前类作为 Spring Bean (组件)
    public static class Config {

        @Bean(name = {"user", "darian-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("darian");
            return user;
        }
    }


}
