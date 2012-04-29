package com.lagnada.xmx1024.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
//@Profile("embedded")
@PropertySource("classpath:jpa-h2.properties")
public class EmbeddedDatabaseConfig {

    @Bean(name = "dataSource")
    public EmbeddedDatabaseFactoryBean embeddedDatabaseFactoryBean() {
        EmbeddedDatabaseFactoryBean factory = new EmbeddedDatabaseFactoryBean();
        factory.setDatabaseType(EmbeddedDatabaseType.H2);
        factory.afterPropertiesSet();
        return factory;
    }

}
