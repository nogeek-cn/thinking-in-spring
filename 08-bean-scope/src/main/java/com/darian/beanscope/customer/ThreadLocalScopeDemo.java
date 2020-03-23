package com.darian.beanscope.customer;

import com.darian.beanscope.customer.util.ThreadLocalScope;
import com.darian.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.atomic.AtomicReference;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/24  2:28
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setName("xxxxx");
        return user;
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        applicationContext.refresh();
        lookupForUser(applicationContext);
        applicationContext.close();
    }

    private static void lookupForUser(AnnotationConfigApplicationContext applicationContext) throws Exception {
        User userBean1 = applicationContext.getBean(User.class);
        User userBean2 = applicationContext.getBean(User.class);

        System.out.println("userBean1: " + userBean1);
        System.out.println("userBean2: " + userBean2);
        System.out.println("userBean1 == userBean2 : " + (userBean1 == userBean2));
        System.out.println("------------------------");

        User userBeanA = getUserByThread(applicationContext);
        User userBeanB = getUserByThread(applicationContext);
        System.out.println("userBeanA: " + userBeanA);
        System.out.println("userBeanB: " + userBeanB);
        System.out.println("userBeanA == userBeanB : " + (userBeanA == userBeanB));
    }

    private static User getUserByThread(AnnotationConfigApplicationContext applicationContext) throws InterruptedException {
        AtomicReference<User> userBean = new AtomicReference<>();
        Thread threadB = new Thread(() -> {
            userBean.set(applicationContext.getBean(User.class));
            System.out.println(Thread.currentThread().getId() + "=====" + userBean.get());
        });
        threadB.start();
        threadB.join();
        return userBean.get();
    }

}
