package com.lagnada.xmx1024.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorInitializer {

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

}
