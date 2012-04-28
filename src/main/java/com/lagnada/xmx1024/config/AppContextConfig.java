package com.lagnada.xmx1024.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(basePackages = {
        "com.lagnada.xmx1024.converter",
        "com.lagnada.xmx1024.dao",
        "com.lagnada.xmx1024.domain",
        "com.lagnada.xmx1024.service"})
public class AppContextConfig {

    @Bean(name = "conversionService")
    protected ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    @Bean(name = "loadTimeWeaver")
    InstrumentationLoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Bean(name = "validator")
    LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
