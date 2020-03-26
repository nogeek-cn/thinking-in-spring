package com.darian.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;


/***
 * BeanDefinition 合并示例
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/25  3:36
 */
public class BeanDefinitionMergeDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //  基于 XML 资源的 XmlBeanDefinitionReader
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        EncodedResource resource = new EncodedResource(new ClassPathResource("META-INF/dependency-lookup-context.xml"));
        beanDefinitionReader.loadBeanDefinitions(resource);

        System.out.println("userBean: \t" + beanFactory.getBean("user"));
        System.out.println("superUserBean: \t" + beanFactory.getBean("superUser"));
    }
}