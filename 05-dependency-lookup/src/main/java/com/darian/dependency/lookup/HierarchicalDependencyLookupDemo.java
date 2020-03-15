package com.darian.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/***
 * 通过 {@link HierarchicalBeanFactory} 进行依赖查找
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/15  4:57
 */
public class HierarchicalDependencyLookupDemo { // @Configuration 是非必须注解
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        // 获取 HierarchicalBeanFactory -< ConfigurableBeanFactory -< ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        System.out.println("当前 BeanFactory 的 parent BeanFactory: " + beanFactory.getParentBeanFactory());
        // 设置 ParentBeanFactory
        DefaultListableBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前 BeanFactory 的 parent BeanFactory: " + beanFactory.getParentBeanFactory());

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");


        dispalyContainsBean(beanFactory, "user");
        dispalyContainsBean(parentBeanFactory, "user");

        applicationContext.refresh();
        applicationContext.close();
    }

    public static void dispalyContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.println("BeanFactory " + beanFactory + "是否包含 bean [name : " + beanName + "] -: "
                + containsBean(beanFactory, beanName));
    }

    public static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    public static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.println("当前 BeanFactory " + beanFactory + "是否包含 bean [name : " + beanName + "] -: "
                + beanFactory.containsLocalBean(beanName));
    }

    private static DefaultListableBeanFactory createParentBeanFactory() {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String locations = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(locations);
        return beanFactory;
    }
}
