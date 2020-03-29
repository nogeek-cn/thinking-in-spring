package com.darian.bean.lifecycle;

import com.darian.bean.customer.MyInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/***
 * Bean 实例化生命周期
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/28  22:26
 */
public class BeanInstantiationLifeCycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();

        System.out.println("-------------------------------------");
        executeApplicationContext();

    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加 BeanPostProcessor 实现（MyInstantiationAwareBeanPostProcessor）
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        //  基于 XML 资源的 XmlBeanDefinitionReader
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = new String[]{"META-INF/dependency-lookup-context.xml",
                "META-INF/bean-constructor-dependency-injection.xml"};

        System.out.println("已加载的 BeanDefinition 数量：\t" + beanDefinitionReader.loadBeanDefinitions(locations));

        System.out.println("userBean: \t" + beanFactory.getBean("user"));
        System.out.println("superUserBean: \t" + beanFactory.getBean("superUser"));

        // 构造器注入按照类型注入， resolveDependency
        System.out.println("userHolderBean: \t" + beanFactory.getBean("userHolder"));

    }

    private static void executeApplicationContext() {
        String[] locations = new String[]{"META-INF/dependency-lookup-context.xml",
                "META-INF/bean-constructor-dependency-injection.xml"};
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);
        // 添加 BeanPostProcessor 实现（MyInstantiationAwareBeanPostProcessor）
        applicationContext.getBeanFactory()
                .addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());


        System.out.println("已加载的 BeanDefinition 数量：\t" + applicationContext.getBeanDefinitionCount());

        System.out.println("userBean: \t" + applicationContext.getBean("user"));
        System.out.println("superUserBean: \t" + applicationContext.getBean("superUser"));

        // 构造器注入按照类型注入， resolveDependency
        System.out.println("userHolderBean: \t" + applicationContext.getBean("userHolder"));
    }


}
