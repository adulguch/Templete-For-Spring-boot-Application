package com.test.AppConfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "app")
@EnableJpaRepositories(basePackages = {
		"com.test.model" }, transactionManagerRef = "jpaTransactionManager")

/*@ComponentScan({ "com.doloop.taskflow.persistence.model", "com.doloop.taskflow.persistence.controller",
		"com.doloop.taskflow.persistence" })*/
@ComponentScan({"com.test.*" })
public class TaskflowJPAConfig {

	@Autowired
	private Environment env;
	
	@Autowired
    HikariDataSource dataSource;


	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.test.model");
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
	}


//	@Bean
//	public DataSource tsdataSource() {
//		HikariDataSource dataSource = new HikariDataSource();
////		dataSource.setDataSourceClassName("");
//		dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
//		dataSource.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
//		dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
//		dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
//		dataSource.setMaximumPoolSize(Integer.parseInt(env.getRequiredProperty("spring.datasource.hikari.max-pool-size")));
//		return dataSource;
//	}
	
	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		hibernateProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		hibernateProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		//hibernateProperties.put("hibernate.event.merge.entity_copy_observer", env.getRequiredProperty("hibernate.merge-entity"));
		
	//	hibernateProperties.put("org.hibernate.envers.default_schema", "audit");
	//	hibernateProperties.put("org.hibernate.envers.audit_table_suffix", "_AUDIT");

		return hibernateProperties;
	}

	/*@Bean
	public HibernateTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}*/
	
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


	@Bean
	public PlatformTransactionManager jpaTransactionManager() {
		final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory1().getObject());
		return jpaTransactionManager ;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory1() {
		final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan(new String[] { "com.test.model" });
		final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setJpaProperties(hibernateProperties());
		
		return emf;
	}
	
}
