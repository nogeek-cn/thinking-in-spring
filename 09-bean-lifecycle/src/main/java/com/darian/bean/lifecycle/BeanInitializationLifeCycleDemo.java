package com.darian.bean.lifecycle;

import com.darian.bean.customer.MyInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;


/***
 * Bean 初始化生命周期
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/28  22:26
 */
public class BeanInitializationLifeCycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //  基于 XML 资源的 XmlBeanDefinitionReader
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加 CommonAnnotationBeanPostProcessor ，解决 @PostConstruct 回调的问题
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = new String[]{"META-INF/dependency-lookup-context.xml",
                "META-INF/bean-constructor-dependency-injection.xml"};

        System.out.println("已加载的 BeanDefinition 数量：\t" + beanDefinitionReader.loadBeanDefinitions(locations));
        // preInstantiateSingletons 将已经注册 的 BeanDefinition 初始化成 Spring Bean
        beanFactory.preInstantiateSingletons();

        System.out.println("userBean: \t" + beanFactory.getBean("user"));
        System.out.println("superUserBean: \t" + beanFactory.getBean("superUser"));

        // 构造器注入按照类型注入， resolveDependency
        System.out.println("userHolderBean: \t" + beanFactory.getBean("userHolder"));

    }
}
