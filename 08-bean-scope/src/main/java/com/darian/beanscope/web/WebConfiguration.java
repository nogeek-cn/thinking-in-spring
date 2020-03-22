package com.darian.beanscope.web;

import com.darian.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
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
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("darian - webConfig");
        return user;
    }
}
