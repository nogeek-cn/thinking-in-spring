package com.darian.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/***
 * {@link BeanCreationException} 示例
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/16  1:51
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(TestModule.class);

        applicationContext.refresh();

        applicationContext.close();
    }

    @Component
    static class TestModule implements InitializingBean {

        @PostConstruct // CommonAnnotationBeanPostProcessor
        public void init() {
            throw new RuntimeException("init() : For purposes ...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet():  For purposes ...");
        }
    }
}
