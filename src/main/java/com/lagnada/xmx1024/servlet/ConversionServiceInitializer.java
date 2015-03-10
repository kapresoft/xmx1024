package com.lagnada.xmx1024.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;

@Configuration
public class ConversionServiceInitializer {

    @Bean(name = "conversionService")
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

}
