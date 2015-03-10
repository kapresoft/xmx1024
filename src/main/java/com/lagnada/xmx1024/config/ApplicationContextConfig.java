package com.lagnada.xmx1024.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@ComponentScan(
        basePackages = "com.lagnada.xmx1024",
        excludeFilters = @ComponentScan.Filter({
                Controller.class,
                Configuration.class
        }),
        includeFilters = @ComponentScan.Filter({
                Component.class,
                Service.class
        })
)
@ImportResource("classpath:property-placeholder.xml")
@Import(value = {
        ConverterRegistryConfig.class,
        JpaConfig.class,
        EmbeddedDatabaseConfig.class
})
@Configuration
public class ApplicationContextConfig {

}
