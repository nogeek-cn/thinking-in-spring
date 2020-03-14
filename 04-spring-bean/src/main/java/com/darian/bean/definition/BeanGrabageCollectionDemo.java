package com.darian.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/***
 * Bean 垃圾回收的代码示例
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/15  1:51
 */
public class BeanGrabageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);

        applicationContext.refresh();
        applicationContext.close();
        System.out.println("Spring 应用上下文 close after.... ... ");

        // 触发 GC
        System.gc();

        // 可能需要等待一些时间
        TimeUnit.SECONDS.sleep(5);

    }
}
