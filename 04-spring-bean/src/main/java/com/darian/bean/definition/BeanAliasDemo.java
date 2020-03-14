package com.darian.bean.definition;

import com.darian.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/13  22:40
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory =  new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

        User user = beanFactory.getBean("user", User.class);
        User darianUser = beanFactory.getBean("darian-user", User.class);

        System.out.println(" user 和 darianUser 是否相等：" + (user == darianUser));
    }
}
