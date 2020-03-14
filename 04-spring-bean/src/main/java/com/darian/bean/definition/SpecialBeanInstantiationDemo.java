package com.darian.bean.definition;

import com.darian.bean.factory.DefaultUserFactory;
import com.darian.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;


/***
 * 特殊的 Bean 实例化
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/14  20:31
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/special-bean-instantiation-context.xml");

        // 通过 ApplicationContext 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        demoServiceLoader();
        ServiceLoader<UserFactory> userFactoryServiceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(userFactoryServiceLoader);

        // 创建 UserFactory 通过 AutowireCapableBeanFactory
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());


    }

    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class,
                Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    public static <T> void displayServiceLoader(ServiceLoader<T> servieLoader) {
        Iterator<T> iterator = servieLoader.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            System.out.print("factory: " + t + "\t");
            System.out.println(UserFactory.class.cast(t).createUser());
        }
    }
}
