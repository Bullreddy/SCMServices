package com.scm.services.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Hello world!
 *
 */
@Configuration
@EnableAutoConfiguration
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
@ComponentScan({"com.scm.services","com.scm.services.dao"})
@EntityScan(basePackages="com.scm.services.dao.entity")
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    @Bean
    public WebMvcConfigurer corsconfigurer() {
    	return new WebMvcConfigurerAdapter() {
    		@Override
    		public void addCorsMappings(CorsRegistry registry) {
    			registry.addMapping("/services/rest/classifications/getClassifications").allowedOrigins("http://localhost:4200");
    			registry.addMapping("/student/saveStudent").allowedOrigins("http://localhost:4200");
    			registry.addMapping("/student/getStudents").allowedOrigins("http://localhost:4200");
    			registry.addMapping("/student/exportStudents").allowedOrigins("http://localhost:4200");
    		}
		};
    }
}
