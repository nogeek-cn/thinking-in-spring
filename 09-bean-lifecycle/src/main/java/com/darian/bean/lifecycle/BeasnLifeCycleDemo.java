package com.darian.bean.lifecycle;

import com.darian.bean.customer.MyDestructionAwareBeanPostProcessor;
import com.darian.bean.customer.MyInstantiationAwareBeanPostProcessor;
import com.darian.domain.SuperUser;
import com.darian.domain.User;
import com.darian.domain.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

import java.util.concurrent.TimeUnit;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/29  15:05
 */
public class BeasnLifeCycleDemo {
    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //  基于 XML 资源的 XmlBeanDefinitionReader
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加 MyDestructionAwareBeanPostProcessor ，
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 添加 CommonAnnotationBeanPostProcessor ，解决 @PostConstruct 回调的问题
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = new String[]{"META-INF/dependency-lookup-context.xml",
                "META-INF/bean-constructor-dependency-injection.xml"};


        System.out.println("已加载的 BeanDefinition 数量：\t" + beanDefinitionReader.loadBeanDefinitions(locations));
        // preInstantiateSingletons 将已经注册 的 BeanDefinition 初始化成 Spring Bean
        beanFactory.preInstantiateSingletons();

        System.out.println("userBean: \t" + beanFactory.getBean("user", User.class));
        System.out.println("superUserBean: \t" + beanFactory.getBean("superUser", SuperUser.class));

        // 构造器注入按照类型注入， resolveDependency
        System.out.println("userHolderBean: \t" + beanFactory.getBean("userHolder", UserHolder.class));

        // 执行 Bean 销毁
        beanFactory.destroyBean("userHolder", beanFactory.getBean("userHolder", UserHolder.class));
        // Bean 销毁并不意味着 Bean 被垃圾回收了。
        System.out.println("userHolderBean: \t" + beanFactory.getBean("userHolder", UserHolder.class));

        // 销毁 BeanFactory 中单例 Bean
        beanFactory.destroySingletons();
        // 强制 GC
        System.gc();

        TimeUnit.SECONDS.sleep(3);
        // 强制 GC
        System.gc();
    }
}
