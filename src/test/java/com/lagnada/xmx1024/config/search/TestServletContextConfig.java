package com.lagnada.xmx1024.config.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagnada.xmx1024.servlet.ServletContextConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
        basePackages = "com.lagnada.xmx1024",
        excludeFilters = @ComponentScan.Filter({Service.class, Repository.class}),
        includeFilters = @ComponentScan.Filter({Component.class, Controller.class, Configuration.class
        }))
@Import(ServletContextConfig.class)
public class TestServletContextConfig {

    @Bean
    ObjectMapper objectMapper()
    {
        return new MappingJackson2HttpMessageConverter().getObjectMapper();
    }

}
