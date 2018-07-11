package com.scm.services.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.scm.services.controller.MultiTenantProperties;

@Component
public class DataSourceConfig {

	@Autowired
	private MultiTenantProperties multitenancyProperties;

	@Bean(name ="dataSourcess")
	@ConfigurationProperties(prefix = "spring.multitenancy.default.datasource")
	public DataSource defaultDataSource() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource().getUsername())
				.password(this.multitenancyProperties.getDatasource().getPassword())
				.url(this.multitenancyProperties.getDatasource().getUrl());
		return factory.build();
	}


}
