package com.teamtreehouse.instateam.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
@PropertySource("app.properties")
public class DataConfig {
    // Autowire a Spring environment object as an instance field
    @Autowired
    private Environment env;

    // Create Spring Bean that's configured on boot and available throughout the application
    // so that Spring is able to manage hibernate sessions in the Spring container application context
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        // Load a ClassPathResource from hibernate.cfg.xml and store it into a Spring resource object
        Resource config = new ClassPathResource("hibernate.cfg.xml");

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // Pass the resource object to the LocalSessionFactoryBean
        sessionFactory.setConfigLocation(config);

        // Use Spring to scan for JPA annotated entities
        // Spring loads all the properties from app.properties and stores them into the environment object
        sessionFactory.setPackagesToScan(env.getProperty("instateam.entity.package"));

        sessionFactory.setDataSource(dataSource());

        return sessionFactory;
    }

    // Use Apache DBCP library to handle database connection pooling
    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();

        // Driver class name
        ds.setDriverClassName(env.getProperty("instateam.db.driver"));

        // Set URL
        ds.setUrl(env.getProperty("instateam.db.url"));

        // Set username & password
        ds.setUsername(env.getProperty("instateam.db.username"));
        ds.setPassword(env.getProperty("instateam.db.password"));

        return ds;
    }
}
