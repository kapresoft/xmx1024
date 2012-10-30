package com.lagnada.xmx1024.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.persistence.EntityManagerFactory;

@Configuration
@Import(value = {
        ConverterRegistryConfig.class,
        JpaConfig.class,
        EmbeddedDatabaseConfig.class
})
public class AppContextConfig {

    @Bean(name = "conversionService")
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txMgr = new JpaTransactionManager();
        txMgr.setEntityManagerFactory(entityManagerFactory);
        txMgr.afterPropertiesSet();
        return txMgr;
    }


}
