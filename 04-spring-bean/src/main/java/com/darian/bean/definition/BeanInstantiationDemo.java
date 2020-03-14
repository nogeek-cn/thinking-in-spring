package com.darian.bean.definition;

import com.darian.domain.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/***
 * Bean 实例化
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/14  20:31
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        ListableBeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/bean-instantiation-context.xml");
        System.out.println(beanFactory.getBean("user-by-static-method", User.class));

        System.out.println(beanFactory.getBean("user-by-bean-factory", User.class));

        System.out.println(beanFactory.getBean("user-by-factory-bean", User.class));

    }
}
