package com.scm.services.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.PersistenceContext;
import javax.servlet.Filter;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.engine.jdbc.connections.spi.DataSourceBasedMultiTenantConnectionProviderImpl;
//import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import  org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.scm.services.common.Constants;
import com.scm.services.serviceimpl.TenantDataSource;

/**
 * Hello world!
 *
 */
@Configuration
@EnableAutoConfiguration
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,SecurityAutoConfiguration.class })
@ComponentScan({"com.scm.services","com.scm.services.dao"})
@EntityScan(basePackages="com.scm.services.dao.entity")
@EnableConfigurationProperties(MultiTenantProperties.class)

public class App extends SpringBootServletInitializer
{
 
    
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class).properties(getProperties());
    }
    
   
    @Bean
    public WebMvcConfigurer corsconfigurer() {
    	return new WebMvcConfigurerAdapter() {
    		
    	
    		@Override
    	        public void addCorsMappings(CorsRegistry registry) {
    	            registry.addMapping("/**");
    	        }
    		
    		@Override
    		public void addInterceptors(InterceptorRegistry registry) {
    			registry.addInterceptor(new MultiTenantInterceptor());
    		}
    		
		};
    }
    
    static Properties getProperties() {
        Properties props = new Properties();
        props.put("spring.config.location", "classpath:config/");
        return props;
     }
    
    

}
