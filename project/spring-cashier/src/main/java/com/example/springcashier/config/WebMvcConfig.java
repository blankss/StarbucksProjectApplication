package com.example.springcashier.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/starbucks").setViewName("starbucks");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");

        registry.addViewController("/access-denied").setViewName("access-denied");
        registry.addViewController("/about-us").setViewName("about-us");
    }

}