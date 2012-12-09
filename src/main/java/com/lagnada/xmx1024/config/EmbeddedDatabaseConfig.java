package com.lagnada.xmx1024.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jpa-h2.properties")
public class EmbeddedDatabaseConfig {

/*    @Bean(name = "dataSource")
    public EmbeddedDatabaseFactoryBean embeddedDatabaseFactoryBean() {
        EmbeddedDatabaseFactoryBean factory = new EmbeddedDatabaseFactoryBean();
        factory.setDatabaseType(EmbeddedDatabaseType.H2);
        factory.afterPropertiesSet();
        return factory;
    }*/

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

}
