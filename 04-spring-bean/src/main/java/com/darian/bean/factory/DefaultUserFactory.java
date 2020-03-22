package com.darian.bean.factory;


import com.darian.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/***
 * {@link UserFactory} 默认实现
 *
 * @author <a href="1934849492@qq.com">Darian</a> 
 * @date 2020/3/14  20:43
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    // 1. 基于 @PostConstruct
    @PostConstruct
    public void init() {
        System.out.println("DefaultUserFactory -  @PostConstruct 初始化中... ... ");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法：DefaultUserFactory#initUserFactory 初始化中... ...  ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet 初始化中... ...  ");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("DefaultUserFactory # @preDestroy 销毁中 ... ...  ");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean # destroy 销毁中 ... ...  ");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法 DefaultUserFactory #  doDestroy 销毁中 ... ...  ");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("当前对象 DefaultUserFactory # finalize 对象正在被回收 ... ... ");
    }
}
