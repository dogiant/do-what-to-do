package com.dogiant.cms.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.dogiant.cms.dao")
@EntityScan(basePackages = "com.dogiant.cms.domain")
public class JPAConfig {

}
