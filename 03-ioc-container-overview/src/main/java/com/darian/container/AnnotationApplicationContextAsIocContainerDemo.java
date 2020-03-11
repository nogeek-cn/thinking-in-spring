package com.darian.container;

import com.darian.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/***
 *注解能力的  {@link ApplicationContext} 作为 IOC 容器
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/11  23:09
 */
@Configuration
public class AnnotationApplicationContextAsIocContainerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 把当前类作为配置类 class
        applicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);
        applicationContext.refresh();
        // 依赖查找集合对象
        lookUpCollectionByType(applicationContext);

        // 关闭容器
        applicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("darian");
        return user;
    }

    private static void lookUpCollectionByType(AnnotationConfigApplicationContext beanFactory) {
        Map<String, User> userMap = beanFactory.getBeansOfType(User.class);
        System.out.println("查找到的所有的集合对象： " + userMap);
    }
}
