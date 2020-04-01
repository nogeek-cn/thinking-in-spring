package com.darian;

import com.darian.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;

/***
 * Bean 配置元信息示例
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/30  3:54
 */
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        // BeanDefinition 的声明

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(User.class)
                .addPropertyValue("name", "darian")
                .getBeanDefinition();
        // 声明 BeanDefinition 的来源
        // 附加属性 (不影响 Bean 的生成 实例化、属性赋值、初始化 === populata、initialize)
        beanDefinition.setAttribute("name", "darian-attribute");
        // 当前 BeanDefinition 来自何方
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition userBeanDefinition = beanFactory.getBeanDefinition("user");
                    // Attribute 是要给属性上下文存储
                    if (BeanConfigurationMetadataDemo.class.equals(userBeanDefinition.getSource())) { // 通过 source 来判断
                        String name = (String) userBeanDefinition.getAttribute("name"); // 就是 darian-attribute
                        User user = (User) bean;
                        user.setName(name);
                    }
                }
                return bean;
            }
        });
        beanFactory.registerBeanDefinition("user", beanDefinition);

        System.out.println(beanFactory.getBean("user"));
    }
}
