package com.scm.services.controller;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import  org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Hello world!
 *
 */
@Configuration
@EnableAutoConfiguration
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,SecurityAutoConfiguration.class })
@ComponentScan({"com.scm.services","com.scm.services.dao","com.scm.services.convertor"})
@EntityScan(basePackages="com.scm.services.dao.entity")
//@EnableConfigurationProperties(MultiTenantProperties.class)

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
    		
/*    		@Override
    		public void addInterceptors(InterceptorRegistry registry) {
    			registry.addInterceptor(new MultiTenantInterceptor());
    		}*/
    		
		};
    }
    
    static Properties getProperties() {
        Properties props = new Properties();
        props.put("spring.config.location", "classpath:config/");
        return props;
     }
    
    

}
