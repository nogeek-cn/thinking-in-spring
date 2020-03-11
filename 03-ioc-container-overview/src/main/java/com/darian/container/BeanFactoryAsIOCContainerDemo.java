package com.darian.container;

import com.darian.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/***
 * IOC 容器示例
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/11  22:55
 */
public class BeanFactoryAsIOCContainerDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String locations = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(locations);
        System.out.println("Bean 定义 加载的数量：" + beanDefinitionsCount);
        // 依赖查找集合对象
        lookUpCollectionByType(beanFactory);
    }

    private static void lookUpCollectionByType(DefaultListableBeanFactory beanFactory) {
        Map<String, User> userMap = beanFactory.getBeansOfType(User.class);
        System.out.println("查找到的所有的集合对象： " + userMap);
    }
}
