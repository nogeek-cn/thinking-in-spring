package com.darian.dependency.injection;

import com.darian.dependency.injection.annotation.UserGroup;
import com.darian.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/***
 * {@link Qualifier} 注解依赖注入
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/17  15:25
 */
@Configuration
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // superUser -> primary = true

    @Autowired
    @Qualifier("user") // 指定 Bean 名称或者 ID
    private User qualifierUserUser;

    // 整体应用上下文存在 4 个 User 类型的 Bean
    // supperUser
    // user
    // user1 -> @Qualifier
    // user2 -> @Qualifier

    @Autowired
    private Collection<User> allUsers; // 2 个 Beans  = user + superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers; // 2 Beans = user1 + user2 -> 4Beans = user1 + user2 + user3 + user4

    @Autowired
    @UserGroup
    private Collection<User> userGroupUsers; // 2 Beans = user3 + user4

    @Bean
    @Qualifier // 进行逻辑分组
    public User user1() {
        return createUserFactory(8L, "user1");
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUserFactory(7L, "user2");
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUserFactory(9L, "user3");
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUserFactory(10L, "user4");
    }


    public static User createUserFactory(Long id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class ( 配置了类 ) -> Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XMl 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user: " + demo.user);
        System.out.println("-------------------------------------------------");
        System.out.println("demo.qualifierUserUser: " + demo.qualifierUserUser);
        System.out.println("-------------------------------------------------");
        System.out.println("demo.allUsers: " + demo.allUsers);
        System.out.println("demo.allUsers.size(): " + demo.allUsers.size());
        System.out.println("-------------------------------------------------");
        System.out.println("demo.qualifiedUsers: " + demo.qualifiedUsers);
        System.out.println("demo.qualifiedUsers.size(): " + demo.qualifiedUsers.size());
        System.out.println("-------------------------------------------------");
        System.out.println("demo.userGroupUsers: " + demo.userGroupUsers);
        System.out.println("demo.userGroupUsers.size(): " + demo.userGroupUsers.size());



        applicationContext.close();
    }
}
