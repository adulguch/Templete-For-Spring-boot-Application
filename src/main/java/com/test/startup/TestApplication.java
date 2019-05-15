package com.test.startup;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.test.AppConfig.TaskflowJPAConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class })
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties({TaskflowJPAConfig.class})
//@EnableJpaRepositories("com.test.*")
//@ComponentScan(basePackages = { "com.test.*" })
@EntityScan("com.test.model") 
@Slf4j
public class TestApplication extends SpringBootServletInitializer implements CommandLineRunner {
	
	@Autowired
    HikariDataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		log.info("Server Started...");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(TestApplication.class);
	}
	
	@Override
	public void run(String... args) throws Exception {
		HikariPool hikariPool = new HikariPool(dataSource);
        System.out.println("DATASOURCE = " + dataSource);
        System.out.println("Total Active Total Connections :" +hikariPool.getActiveConnections() +" total Total Connections :" +hikariPool.getTotalConnections());
        System.out.println("SCEMA NAME = " + dataSource.getConnection().getCatalog());

    }
	

}
