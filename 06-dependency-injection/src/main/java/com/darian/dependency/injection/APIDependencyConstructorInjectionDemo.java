package com.darian.dependency.injection;

import com.darian.domain.SuperUser;
import com.darian.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/***
 * 基于 API 资源的依赖 Constructor 方法注入示例
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/17  1:18
 */
public class APIDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(APIDependencyConstructorInjectionDemo.class);

        // 生成 UserHolderBeanDefinition 并注册
        applicationContext.registerBeanDefinition("userHolder", createUserHolderBeanDefinition());

        applicationContext.refresh();
        System.out.println(applicationContext.getBean("userHolder"));
        applicationContext.close();
    }

    /**
     * 为 {@link UserHolder} 生成 {@link BeanDefinition}
     *
     * @return
     */
    public static BeanDefinition createUserHolderBeanDefinition() {
        return BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class)
                .addConstructorArgReference("superUser") // setter 的顺序不确定的，构造器是固定的。顺序要正确
                .getBeanDefinition();
    }

    @Bean
    public User superUser() {
        SuperUser superUser = new SuperUser();
        superUser.setAddress("杭州");
        superUser.setId(1L);
        superUser.setName("darian");
        return superUser;
    }
}
