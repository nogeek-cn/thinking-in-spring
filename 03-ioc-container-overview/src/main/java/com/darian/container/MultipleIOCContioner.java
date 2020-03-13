package com.darian.container;

import com.darian.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Map;

/***
 * 一个父级 Spring 下有多个 子模块。然后呢，每个模块一个 Spring 上下文，同一一个上下文。
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/12  4:17
 */
public class MultipleIOCContioner {
    public static void main(String[] args) {
        DefaultListableBeanFactory rootBeanFactory = new DefaultListableBeanFactory();

        DefaultListableBeanFactory beanFactory1 = getDefaultListableBeanFactory1(rootBeanFactory);
        DefaultListableBeanFactory beanFactory2 = getDefaultListableBeanFactory2(rootBeanFactory);
        // 依赖查找集合对象
        lookUpCollectionByType(beanFactory1);
        lookUpCollectionByType(beanFactory2);

        System.out.println(beanFactory1.getParentBeanFactory() == beanFactory2.getParentBeanFactory());


    }

    private static DefaultListableBeanFactory getDefaultListableBeanFactory2(DefaultListableBeanFactory applicationContext) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory(applicationContext);
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String locations = "classpath:/META-INF/dependency-lookup-context-2.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(locations);
//        System.out.println("Bean 定义 加载的数量：" + beanDefinitionsCount);
        return beanFactory;
    }

    private static DefaultListableBeanFactory getDefaultListableBeanFactory1(DefaultListableBeanFactory applicationContext) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory(applicationContext);
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String locations = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(locations);
//        System.out.println("Bean 定义 加载的数量：" + beanDefinitionsCount);
        return beanFactory;
    }

    private static void lookUpCollectionByType(DefaultListableBeanFactory beanFactory) {
        Map<String, User> userMap = beanFactory.getBeansOfType(User.class);
        System.out.println("查找到的所有的集合对象： " + userMap);
    }
}
