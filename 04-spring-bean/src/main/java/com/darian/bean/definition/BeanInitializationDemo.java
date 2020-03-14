package com.darian.bean.definition;

import com.darian.bean.factory.DefaultUserFactory;
import com.darian.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/***
 * Bean 初始化 Demo
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/15  0:30
 */
@Configuration // @Configuration class
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        // 非延迟初始化在 Spring 应用上下文完成后，被初始化

        System.out.println("applicationContext.refresh() 完成。。。。。");
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("applicationContext.close() before 。。。。。");
        applicationContext.close();
        System.out.println("applicationContext.close() after 。。。。。");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy(value = false)
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
