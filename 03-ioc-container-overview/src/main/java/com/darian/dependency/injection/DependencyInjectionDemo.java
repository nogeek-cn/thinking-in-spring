package com.darian.dependency.injection;

import com.darian.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;



/***
 * 依赖注入示例
 * 1. 根据名称来查找
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/9  14:53
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) throws Exception {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/dependency-injection-context.xml");
        // 两个应用上下文 injection lookup

        // 依赖来源一： (自定义的 Bean)
        UserRepository userRepository1 = beanFactory.getBean("userRepository1", UserRepository.class);
        System.out.println(userRepository1.getUsers());

        System.out.println(userRepository1.getBeanFactory());
        System.out.println(userRepository1.getBeanFactory() == beanFactory);

        // 自动注入的 方式 :
        UserRepository userRepository2 = beanFactory.getBean("userRepository2", UserRepository.class);
        System.out.println(userRepository2.getUsers());


        // 依赖注入 ( 内建依赖 )
        System.out.println(userRepository2.getBeanFactory()); // DefaultListableBeanFactory
        System.out.println(userRepository2.getBeanFactory() == beanFactory);
        // org.springframework.beans.factory.support.DefaultListableBeanFactory@4efbca5a: defining beans
        // [user,superUser,objectFactory,userRepository1,userRepository2]; root of factory hierarchy

        // 没有这个 Bean 的定义，报错，那么这个，那么就说明，依赖注入和依赖查找是不一样的。
        //依赖查找 ( 错误 )
//        System.out.println(beanFactory.getBean(BeanFactory.class));


        // 结论，依赖查找和依赖注入都是依赖，但是他们不是同源。
        // BeanFactory 不是一个普通的 Bean ，那么它又是什么呢？

        // ClassPathXmlApplicationContext
        ObjectFactory<ApplicationContext> objectFactory = userRepository2.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == beanFactory); // true


        // 容器内建的 Bean , 并不是业务方，也不是我们自己的应用帮助我们构建的。
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean: " + environment);
        // 获取 Environment 类型的 Bean: StandardEnvironment {activeProfiles=[], defaultProfiles=[default],
        // propertySources=[PropertiesPropertySource {name='systemProperties'}, SystemEnvironmentPropertySource {name='systemEnvironment'}]}
    }


}
