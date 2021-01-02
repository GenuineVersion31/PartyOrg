package com.party_org.demo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author create by 李若阳
 * @description: com.party_org.demo
 * Created on 2020/4/7-3:29 下午
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
//提供简易的注册服务
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/test").setViewName("index3");
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/activity").setViewName("/dashboard/Activity");
//        registry.addViewController("/authority").setViewName("/dashboard/Authority");
//        registry.addViewController("/group").setViewName("/dashboard/PartyGroup");
//        registry.addViewController("/ready").setViewName("/dashboard/ReadyGroup");
//        registry.addViewController("/porter").setViewName("/dashboard/PorterControll");
//        registry.addViewController("/information").setViewName("/dashboard/UserList");
//        registry.addViewController("/userlist").setViewName("/dashboard/UserList");
//        registry.addViewController("/userslist").setViewName("/dashboard/UsersList");
//        registry.addViewController("/page-login").setViewName("/dashboard/page-login");
//        registry.addViewController("/").setViewName("");
    }
}
