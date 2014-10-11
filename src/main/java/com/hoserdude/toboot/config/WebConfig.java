package com.hoserdude.toboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Not every view needs a controller, so you can handle the routing
 * for such beasts by defining a dynamic view controller.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/openId").setViewName("openId");
        registry.addViewController("/api").setViewName("api");
        registry.addViewController("/todo").setViewName("todo");
        registry.addViewController("/terms").setViewName("terms");
    }

}
