package com.darian.beanscope;


import com.darian.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

import java.util.Map;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/22  17:55
 */
public class BeanScopeDemo implements DisposableBean {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // 默认配置就是 singleton
    public static User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return createUser();
    }

    public static User createUser() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser2;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser3;

    @Autowired
    public Map<String, User> userMap;
    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.println("类：【" + bean.getClass().getSimpleName() + "】 Bean 名称：【" + beanName
                            + "】在初始化之后回调...");
                    return bean;
                }
            });
        });
        applicationContext.refresh();

        // 结论一：
        // singleton Bean 无论是依赖查找还是依赖注入，均为同一个对象
        // prototype Bean 无论是依赖查找还是依赖注入，均为新生成的对象

        // 结论二：
        // 如果依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 均会存在一个
        // Prototype Bean 有别于其他地方的依赖注入 Prototype Bean ,

        // 结论三：
        // 无论是 Singleton Bean 还是 Prototype Bean 均会执行初始化方法回调，
        // 不过仅 singleton Bean 会执行销毁方法回调

        scopeBeansByLookup(applicationContext);
        scopeBeansByInjection(applicationContext);


        applicationContext.close();
    }

    private static void scopeBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("------------------scopeBeansByInjection------------------");
        BeanScopeDemo beanScopeDemoBean = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("beanScopeDemoBean.singletonUser1: \t" + beanScopeDemoBean.singletonUser1);
        System.out.println("beanScopeDemoBean.singletonUser2: \t" + beanScopeDemoBean.singletonUser2);
        System.out.println("beanScopeDemoBean.prototypeUser1: \t" + beanScopeDemoBean.prototypeUser1);
        System.out.println("beanScopeDemoBean.prototypeUser2: \t" + beanScopeDemoBean.prototypeUser2);
        System.out.println("beanScopeDemoBean.prototypeUser3: \t" + beanScopeDemoBean.prototypeUser3);
        System.out.println("beanScopeDemoBean.userMap: \t" + beanScopeDemoBean.userMap);


    }

    private static void scopeBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("------------------scopeBeansByLookup------------------");
        System.out.println("singletonUser == : \t" + (applicationContext.getBean("singletonUser")));
        System.out.println("singletonUser == : \t" + (applicationContext.getBean("singletonUser")));
        System.out.println("singletonUser == : \t" + (applicationContext.getBean("singletonUser")));

        System.out.println("prototypeUser == : \t" + (applicationContext.getBean("prototypeUser")));
        System.out.println("prototypeUser == : \t" + (applicationContext.getBean("prototypeUser")));
        System.out.println("prototypeUser == : \t" + (applicationContext.getBean("prototypeUser")));

        System.out.println("======================");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("当前  Bean ScopeDemo Bean 正在销毁中.....");
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        this.prototypeUser3.destroy();

        // 获取 Bean Definition
        for (Map.Entry<String, User> stringUserEntry : this.userMap.entrySet()) {
            String beanName = stringUserEntry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()){ // 如果当前 Bean 是 prototype scope
                User user = stringUserEntry.getValue();
                user.destroy();
            }

        }
        System.out.println("当前  Bean ScopeDemo Bean 销毁完整.....");

    }
}
