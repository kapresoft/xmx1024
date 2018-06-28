package com.lagnada.xmx1024.config;

import com.lagnada.xmx1024.servlet.InternationalizationInitializer;
import com.lagnada.xmx1024.servlet.ValidatorInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@ComponentScan(
        basePackages = "com.lagnada.xmx1024",
        excludeFilters = @ComponentScan.Filter({
                Controller.class,
                Configuration.class
        }),
        includeFilters = @ComponentScan.Filter({
                Component.class,
                Repository.class,
                Service.class
        })
)
@ImportResource("classpath:property-placeholder.xml")
@Import(value = {
        InternationalizationInitializer.class,
        ConversionServiceInitializer.class,
        ValidatorInitializer.class,
        JpaInitializer.class,
        EmbeddedDatabaseInitializer.class
})
@Configuration
public class ApplicationContextConfig {

}
