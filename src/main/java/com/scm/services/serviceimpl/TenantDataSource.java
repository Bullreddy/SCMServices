package com.scm.services.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.scm.services.dao.*;

import com.scm.services.dao.entity.Datasourceconfig;

@Component
public class TenantDataSource implements Serializable{

    private HashMap<String, DataSource> dataSources = new HashMap<>();

    @Autowired
    private DataSoureConfigDAO configRepo;

    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) {
            dataSources.put(name, dataSource);
        }
        return dataSource;
    }

    public Map<String, DataSource> getAll() {
        List<Datasourceconfig> configList = configRepo.getAllDataSource();
        Map<String, DataSource> result = new HashMap<>();
        for (Datasourceconfig config : configList) {
            DataSource dataSource = getDataSource(config.getName());
            result.put(config.getName(), dataSource);
        }
        return result;
    }

    private DataSource createDataSource(String name) {
    	Datasourceconfig config = configRepo.getDatasourceByName(name);
        if (config != null) {
            DataSourceBuilder factory = DataSourceBuilder
                    .create().driverClassName(config.getDriverclassname())
                    .username(config.getUsername())
                    .password(config.getPassword())
                    .url(config.getUrl());
            DataSource ds = factory.build();
           
            return ds;
        }
        return null;
    }

    private void initialize(DataSource dataSource) {
        ClassPathResource schemaResource = new ClassPathResource("schema.sql");
        ClassPathResource dataResource = new ClassPathResource("data.sql");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(schemaResource, dataResource);
        populator.execute(dataSource);
    }


}