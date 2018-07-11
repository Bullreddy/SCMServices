package com.scm.services.controller;


import java.util.List;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
@ConfigurationProperties("spring.multitenancy.default")
public class MultiTenantProperties {

	@NestedConfigurationProperty
	private DataSourceProperties datasource;

	

	public DataSourceProperties getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSourceProperties datasource1) {
		this.datasource = datasource1;
	}

}