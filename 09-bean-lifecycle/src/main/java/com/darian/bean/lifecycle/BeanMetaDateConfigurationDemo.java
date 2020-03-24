package com.darian.bean.lifecycle;

import com.darian.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/***
 * Bean 元信息配置 Demo
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/25  0:22
 */
public class BeanMetaDateConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        EncodedResource resource = new EncodedResource(new ClassPathResource("META-INF/user.properties"), "UTF-8");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(resource);

        System.out.println("已加载 BeanDefinition 数量：\t" + beanNumbers);

        User userBean = beanFactory.getBean("user", User.class);
        System.out.println("userBean: \t" + userBean);
    }
}
