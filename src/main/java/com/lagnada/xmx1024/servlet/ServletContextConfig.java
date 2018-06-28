package com.lagnada.xmx1024.servlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @see com.lagnada.xmx1024.servlet.DispatcherServletInitializer
 */
@Configuration
@ComponentScan(
        basePackages = "com.lagnada.xmx1024",
        excludeFilters = @ComponentScan.Filter({Service.class, Repository.class}),
        includeFilters = @ComponentScan.Filter({Component.class, Controller.class, Configuration.class
        }))
public class ServletContextConfig extends WebMvcConfigurationSupport {

    @Value("${viewResolver.prefix:/WEB-INF/jsp/}")
    private String viewResolverPrefix;

    @Bean
    public InternalResourceViewResolver InternalResourceViewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(viewResolverPrefix);
        viewResolver.setExposeContextBeansAsAttributes(true);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewNames("*");
        viewResolver.setAlwaysInclude(true);
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/css/")
                .addResourceLocations("/css/**");
        registry.addResourceHandler("/bootstrap/")
                .addResourceLocations("/bootstrap/**");
    }


}
