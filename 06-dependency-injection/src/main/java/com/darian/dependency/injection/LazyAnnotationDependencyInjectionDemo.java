package com.darian.dependency.injection;

import com.darian.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/***
 * {@link ObjectProvider} 注解依赖注入
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/17  15:25
 */
@Configuration
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private ObjectProvider<User> userObjectProvider; // superUser -> primary = true

    @Autowired
    private ObjectFactory<Set<User>> userObjectFactorySet;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class ( 配置了类 ) -> Spring Bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XMl 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.userObjectProvider: " + demo.userObjectProvider);
        System.out.println("-------------------------------------------------");
        System.out.println("demo.userObjectProvider.getObject(): " + demo.userObjectProvider.getObject()); // 继承了 ObjectFactory
        System.out.println("-------------------------------------------------");
        demo.userObjectProvider.forEach(System.out::println);
        System.out.println("-------------------------------------------------");

        System.out.println("demo.userObjectFactorySet.getObject(): " + demo.userObjectFactorySet.getObject()); // 继承了 ObjectFactory
        demo.userObjectFactorySet.getObject().forEach(System.out::println);

        applicationContext.close();
    }
}
