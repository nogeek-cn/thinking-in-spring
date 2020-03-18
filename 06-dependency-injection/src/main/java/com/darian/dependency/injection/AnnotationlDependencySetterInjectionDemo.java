package com.darian.dependency.injection;

import com.darian.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


/***
 * 基于 Annotation 资源的依赖 Setter 方法注入示例
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/16  23:20
 */
public class AnnotationlDependencySetterInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XMl 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.register(AnnotationlDependencySetterInjectionDemo.class);
        applicationContext.refresh();

        System.out.println(applicationContext.getBean("userHolder1"));
        System.out.println(applicationContext.getBean("userHolder2"));


        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder1(User user) {
        return new UserHolder(user);
    }

    @Bean
    public UserHolder userHolder2(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
