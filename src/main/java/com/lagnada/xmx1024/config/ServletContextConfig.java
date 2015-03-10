package com.lagnada.xmx1024.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(
        basePackages = "com.lagnada.xmx1024",
        excludeFilters = @ComponentScan.Filter(Service.class),
        includeFilters = @ComponentScan.Filter({
                Component.class,
                Controller.class,
                Configuration.class
        }))
public class ServletContextConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/")
                .addResourceLocations("/css/**");
        registry.addResourceHandler("/bootstrap/")
                .addResourceLocations("/bootstrap/**");
    }

}
