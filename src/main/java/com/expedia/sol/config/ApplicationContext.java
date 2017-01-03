package com.expedia.sol.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.validation.Validator;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.dao.impl.StatusHibernateDbAccessor;
import com.expedia.sol.dao.impl.ListStatusRequest;
import com.expedia.sol.domain.Activity;
import com.expedia.sol.domain.Status;
import com.expedia.sol.provider.PropertyProvider;
import com.expedia.sol.validator.ReportValidator;
import com.expedia.sol.validator.StatusValidator;

@Configuration
public class ApplicationContext {
	
	@Value("${db.dblocation}")
	private String dbLocation;
	
	@Bean
	public PropertyPlaceholderConfigurer propertyConfigurer() throws IOException {
	    PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
	    props.setLocations(new Resource[] {new ClassPathResource("config.properties")});
	    return props;
	}
	
	@Bean(name = "hibernateDBAccessor")
	public IDBAccessor<Status, ListStatusRequest> getDummyAccessor() {
		return new StatusHibernateDbAccessor();
	}
	
	@Bean
	public PropertyProvider getPropertyProvider() {
		return new PropertyProvider();
	}
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("org.sqlite.JDBC");
	    String workingDir = System.getProperty("user.dir");
	    System.out.println("db: " + workingDir);
	    dataSource.setUrl("jdbc:sqlite:"+ workingDir +"/timetracker.db");
	    dataSource.setUsername("");
	    dataSource.setPassword("");
	 
	    return dataSource;
	}
	
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 
	    sessionBuilder.addAnnotatedClasses(Status.class, Activity.class);
	    sessionBuilder.addProperties(getHibernateProperties());
	 
	    return sessionBuilder.buildSessionFactory();
	}
	
	@Bean(name = "statusValidator")
	public Validator geStatustValidator() {
		return new StatusValidator();
	}
	
	@Bean(name = "reportValidator")
	public Validator getReportValidator() {
		return new ReportValidator();
	}
	
	//@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	 
	    return transactionManager;
	}
	
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    return properties;
	}

}
