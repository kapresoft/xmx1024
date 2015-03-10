package com.lagnada.xmx1024.servlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class JspInitializer {

    @Bean(name = {"viewResolver"})
    public ViewResolver viewResolver(@Value("${viewResolver.prefix:/WEB-INF/jsp/}") String viewResolverPrefix) {
        Assert.hasText(viewResolverPrefix);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(viewResolverPrefix);
        viewResolver.setExposeContextBeansAsAttributes(true);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewNames(new String[]{"*"});
        viewResolver.setAlwaysInclude(true);
        viewResolver.setOrder(1);
        return viewResolver;
    }

}
