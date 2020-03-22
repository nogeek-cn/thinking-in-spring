package com.darian.dependency.injection;

import com.darian.dependency.injection.annotation.MyAutowired;
import com.darian.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Map;
import java.util.Optional;

/***
 * 注解驱动的依赖注入处理过程
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/17  15:25
 */
@Configuration
public class AnnotationDependencyInjectionResolutionDemo {
    @Autowired              //去依赖查找（处理) + 延迟
    @Lazy
    private User userLazy;

    @Autowired              //去依赖查找（处理
    private User user;      // DependencyDescriptor -> 必须 实时注入（required = true） + 实时注入（engaer = true）
    //                      // 通过类型(User.class)）+ 字段名称（"user"） + 是否 (primary = true)

    @Autowired
    private Map<String, User> userMap;

    @Autowired
    private Optional<User> userOptional;

    @Inject
    private User injecteduser;

    @MyAutowired
    private User userMyAutowired;



    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class ( 配置了类 ) -> Spring Bean
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XMl 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo.user: " + demo.user);
        System.out.println("-------------------------------------------------");

        System.out.println("demo.userMap: " + demo.userMap);
        System.out.println("-------------------------------------------------");

        System.out.println("demo.userOptional: " + demo.userOptional);
        System.out.println("-------------------------------------------------");

        System.out.println("demo.userLazy: " + demo.userLazy);
        System.out.println("-------------------------------------------------");
        System.out.println("demo.userMyAutowired: " + demo.userMyAutowired);

        applicationContext.close();
    }
}
