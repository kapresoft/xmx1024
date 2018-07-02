package com.lagnada.xmx1024.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY)
//@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
public class JpaInitializer {

    private static final String PERSISTENCE_UNIT_NAME = "jpa";
    private static final String PACKAGES_TO_SCAN = "com.lagnada.xmx1024";

    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txMgr = new JpaTransactionManager();
        txMgr.setEntityManagerFactory(entityManagerFactory);
        txMgr.afterPropertiesSet();
        return txMgr;
    }

    @Bean(name = "jpaAdapter")
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter(
            @Value("${jpa.database}") Database database,
            @Value("${jpa.show.sql}") boolean showSql,
            @Value("${jpa.generate.ddl}") boolean generateDdl) {
        HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        jpaAdapter.setDatabase(database);
        jpaAdapter.setShowSql(showSql);
        jpaAdapter.setGenerateDdl(generateDdl);
        return jpaAdapter;
    }

    @Bean(name = "loadTimeWeaver")
    public InstrumentationLoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor PersistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
            DataSource dataSource, LoadTimeWeaver loadTimeWeaver, JpaVendorAdapter jpaAdapter,
            @Qualifier("jpaProperties") Properties jpaProperties) {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan(PACKAGES_TO_SCAN);
        factory.setDataSource(dataSource);
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        factory.setLoadTimeWeaver(loadTimeWeaver);
        factory.setJpaVendorAdapter(jpaAdapter);
        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    @Qualifier("jpaProperties")
    public Properties jpaProperties(@Value("${hibernate.generate_statistics}") String generateStatistics,
                                     @Value("${hibernate.cache.use_structured_entries}") String structuredEntries,
                                     @Value("${hibernate.use_sql_comments}") String useSqlComments,
                                     @Value("${hibernate.jdbc.batch_size}") String batchSize) {
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.generate_statistics", generateStatistics);
        jpaProperties.setProperty("hibernate.use_structured_entries", structuredEntries);
        jpaProperties.setProperty("hibernate.use_sql_comments", useSqlComments);
        jpaProperties.setProperty("hibernate.jdbc.batch_size", batchSize);
        return jpaProperties;
    }

}
