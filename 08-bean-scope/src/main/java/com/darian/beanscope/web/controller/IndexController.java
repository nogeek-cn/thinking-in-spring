package com.darian.beanscope.web.controller;

import com.darian.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 首页 Spring Web MVC Controller
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/3/23  2:17
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private User user;

    @GetMapping("index.html")
    public String index(Model model) {
        model.addAttribute("user", user);
        model.addAttribute("userIdentityHashCode" , System.identityHashCode(user));
        return "index";
    }
}
