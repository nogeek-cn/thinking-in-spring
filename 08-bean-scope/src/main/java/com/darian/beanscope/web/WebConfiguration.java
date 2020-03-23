package com.darian.beanscope.web;

import com.darian.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/***
 * Spring web MVC 配置类
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/23  3:00
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    @RequestScope
    public User userRequest() {
        User user = new User();
        user.setId(1L);
        user.setName("darian - userRequest");
        return user;
    }

    @Bean
    @SessionScope
    public User userSession() {
        User user = new User();
        user.setId(2L);
        user.setName("darian - userSession");
        return user;
    }

    @Bean
    @ApplicationScope
    public User userApplication() {
        User user = new User();
        user.setId(2L);
        user.setName("darian - userApplication");
        return user;
    }

}
