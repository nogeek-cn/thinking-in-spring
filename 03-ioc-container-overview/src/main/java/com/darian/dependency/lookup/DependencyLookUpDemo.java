package com.darian.dependency.lookup;

import com.darian.annotation.Super;
import com.darian.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;


/***
 * 依赖查找示例
 * 1. 根据名称来查找
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/9  14:53
 */
public class DependencyLookUpDemo {
    public static void main(String[] args) throws Exception {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/dependency-lookup-context.xml");

        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);
        // 根据类型去查找
        lookupByType(beanFactory);
        // 根据类型去查找 Collection
        lookupByCollectionType(beanFactory);
        // 通过注解查找对象
        lookupByAnnationType(beanFactory);


    }

    private static void lookupByAnnationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = ListableBeanFactory.class.cast(beanFactory);
            Map<String, Object> beansWithAnnotation = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找所有的标注 @Super 注解的对象：" + beansWithAnnotation);
        }
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = ListableBeanFactory.class.cast(beanFactory);
            Map<String, User> userBeansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 的集合对象：" + userBeansOfType);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        // spring 3.x 以后支持 java 5 反省
        User user = beanFactory.getBean(User.class);
        System.out.println("lookupByType");
        System.out.println(user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) throws Exception {
        ObjectFactory<User> userObjectFactory = (ObjectFactory) beanFactory.getBean("objectFactory");
        System.out.println("延迟查找");
        System.out.println(userObjectFactory.getObject());
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        System.out.println("实时查找");
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }
}
