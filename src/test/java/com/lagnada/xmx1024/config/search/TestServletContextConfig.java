package com.lagnada.xmx1024.config.search;

import com.lagnada.xmx1024.servlet.ServletContextConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
}
