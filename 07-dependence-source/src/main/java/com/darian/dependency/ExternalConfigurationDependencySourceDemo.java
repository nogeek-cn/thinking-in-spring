package com.darian.dependency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/22  5:04
 */
@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-2}")
    public Long id;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;

    @Value("${userPro.name}")
    private String userName;

    @PostConstruct
    public void init() {
        System.out.println("id: " + id);
        System.out.println("resource: " + resource);
        System.out.println("userName: " + userName);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);


        applicationContext.refresh();
        applicationContext.close();
    }
}
