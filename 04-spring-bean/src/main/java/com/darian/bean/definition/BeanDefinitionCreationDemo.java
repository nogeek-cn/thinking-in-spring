package com.darian.bean.definition;

import com.darian.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/***
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建示例
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/12  23:14
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1. 通过 BeanDefinitionBuilder 来构造。
        // 顶层的 rootBeanDefinition 不能又 parent
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性
        beanDefinitionBuilder.addPropertyValue("age", 20)
                .addPropertyValue("name", "darian");
        // 获取 BeanDefinition 示例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // beanDefinition 并非 BeanDefinition 终态，可以自定义修改

        // 通过 AbstractBeanDefinition 或者它的派生类来操作
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("age", 20)
                .add("name", "darian");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
