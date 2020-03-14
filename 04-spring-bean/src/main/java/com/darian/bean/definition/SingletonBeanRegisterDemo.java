package com.darian.bean.definition;

import com.darian.bean.factory.DefaultUserFactory;
import com.darian.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/***
 * 单体 Bean 注册示例
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/15  2:01
 */
public class SingletonBeanRegisterDemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 创建了一个外部的 UserFactory 对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 注册外部的单例对象
        beanFactory.registerSingleton("userFactory", userFactory);

        applicationContext.refresh();

        UserFactory userFactoryByLookUp = beanFactory.getBean("userFactory", UserFactory.class);

        System.out.println("userFactory == userFactoryByLookUp :  " + (userFactory == userFactoryByLookUp));

        applicationContext.close();
    }
}
