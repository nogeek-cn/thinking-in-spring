package com.darian.dependency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/***
 * 依赖来源示例
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/21  19:58
 */
public class DependenceSourceDemo {

    // 注入在 #postProcessProperties 方法执行，早于 Setter 注入，也早于 @PostConstruct
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private UserDemo userDemo;

    @PostConstruct
    public void init() {
        System.out.println("beanFactory == applicationContext: " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory: "
                + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext: " + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext: "
                + (applicationEventPublisher == applicationContext));
        System.out.println("registerResolvableDependency : " + userDemo);
    }

    @PostConstruct
    public void intByLookup() {
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationContext.class);
        getBean(ApplicationEventPublisher.class);
        getBean(UserDemo.class);
    }

    private <T> T getBean(Class<T> beanType) {
        T bean = null;
        try {
            beanFactory.getBean(beanType);

        } catch (Exception e) {
            System.err.println(e.getClass().getSimpleName() + " : " + e.getMessage());
        }
        System.out.println("[ " + beanType.getSimpleName() + "] bean : " + bean);

        return bean;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        UserDemo userDemo = new UserDemo("userDemo.class");

        applicationContext.getDefaultListableBeanFactory().registerResolvableDependency(UserDemo.class, userDemo);
        applicationContext.register(DependenceSourceDemo.class);


        applicationContext.refresh();

        DependenceSourceDemo demoBean = applicationContext.getBean(DependenceSourceDemo.class);


        applicationContext.close();
    }

    public static class UserDemo {
        private String name;

        public UserDemo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "UserDemo{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
