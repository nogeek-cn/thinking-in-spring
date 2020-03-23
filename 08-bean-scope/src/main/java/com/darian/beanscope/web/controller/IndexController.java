package com.darian.beanscope.web.controller;

import com.darian.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestScope;

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
    @Qualifier("userRequest")
    private User userRequest; // userRequest 是 CGLib 代理以后的对象（不变）
    @Autowired
    @Qualifier("userSession")
    private User userSession;
    @Autowired
    @Qualifier("userApplication")
    private User userApplication;

    @GetMapping("index.html")
    public String index(Model model) {
        model.addAttribute("userRequest", userRequest);
        model.addAttribute("userSession", userSession);

        // userApplcation -> 渲染上下文
        // user 对象存在 ServletContext，上下文名称：scopedTarget.userApplication(ServletContext)
        model.addAttribute("userApplicationModle", userApplication);


        return "index";
    }

    public static void main(String[] args) {
        Object key = (54345643 + (256518 << 16));
        int h;

        int i = (h = key.hashCode()) ^ (h >> 16);
        System.out.println("key:[" + key + "], \thashCode:[" + key.hashCode() + "]，\thashmapHashCode:[" + i + "]"
                + "\tkey.hashCode()%16：[" + (key.hashCode() ^ 15) + "]，\thashmapHashCode%16:[" + (i ^ 15) + "]");

        key = (54345643 + (256519 << 16));
        i = (h = key.hashCode()) ^ (h >> 16);
        System.out.println("key:[" + key + "], \thashCode:[" + key.hashCode() + "]，\thashmapHashCode:[" + i + "]"
                + "\tkey.hashCode()%16：[" + (key.hashCode() ^ 15) + "]，\thashmapHashCode%16:[" + (i ^ 15) + "]");

        key = (54345643 + (256520 << 16));
        i = (h = key.hashCode()) ^ (h >> 16);
        System.out.println("key:[" + key + "], \thashCode:[" + key.hashCode() + "]，\thashmapHashCode:[" + i + "]"
                + "\tkey.hashCode()%16：[" + (key.hashCode() ^ 15) + "]，\thashmapHashCode%16:[" + (i ^ 15) + "]");
    }
}
