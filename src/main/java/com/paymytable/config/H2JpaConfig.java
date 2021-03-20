package com.paymytable.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Datasource configuration class
 */
@Configuration
@EnableJpaRepositories("com.paymytable.dao")
@EntityScan("com.paymytable.entity")
@PropertySource(value = "classpath:/h2.properties")
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class H2JpaConfig {

}
