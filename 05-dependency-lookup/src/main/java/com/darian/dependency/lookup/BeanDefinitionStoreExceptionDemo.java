package com.darian.dependency.lookup;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * {@link BeanDefinitionStoreException} 示例
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/16  1:58
 */
public class BeanDefinitionStoreExceptionDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("xxx.xml");
        applicationContext.refresh();
        applicationContext.close();
    }
}
