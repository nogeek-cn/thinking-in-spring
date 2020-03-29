package com.darian.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/29  0:40
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        EnvironmentAware, InitializingBean, SmartInitializingSingleton, DisposableBean {
    private final User user;
    private Integer number;
    private String description;
    // Aware
    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;
    private Environment environment;

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", classLoader=" + classLoader +
                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                ", environment=" + environment +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.err.println("#setBeanClassLoader >>>>");
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("#setBeanFactory >>>>");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.err.println("#setBeanName >>>>");
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.err.println("#setEnvironment >>>>");
        this.environment = environment;
    }

    // 依赖注解驱动
    @PostConstruct
    public void initPostConstruct() {
        this.description = this.description + " -->>  @PostConstruct v4";
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = this.description + " -->>  #afterPropertiesSet v5";
    }

    public void init() {
        this.description = this.description + " -->>  #customerInit v6";
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.description = this.description + " -->>  #afterSingletonsInstantiated v8";
    }

    @PreDestroy
    public void destroyPreDestroy() {
        this.description = this.description + " -->>  @PreDestroy v10";
    }

    @Override
    public void destroy() throws Exception {
        this.description = this.description + " -->>  #destroy v11";
    }

    public void doDestroy() {
        this.description = this.description + " -->>  #doDestroy v12";
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.err.println("com.darian.domain.UserHolder#finalize() ... ... ");
    }
}
