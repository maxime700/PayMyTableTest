package com.paymytable;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@ComponentScan({
        "com.paymytable.service",
        "com.paymytable.dao"
})
@EnableJpaRepositories(basePackages = {"com.paymytable.dao"})
@EntityScan("com.paymytable.entity")
public class SpringRootTestConfig {




}
