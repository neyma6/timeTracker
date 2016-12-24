package com.expedia.sol.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.dao.impl.DummyDbAccessor;
import com.expedia.sol.dao.impl.HibernateDbAccessor;
import com.expedia.sol.domain.Status;

@Configuration
public class ApplicationContext {
	
	@Bean
	public PropertyPlaceholderConfigurer propertyConfigurer() throws IOException {
		System.out.println("placholdr");
	    PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
	    props.setLocations(new Resource[] {new ClassPathResource("config.properties")});
	    return props;
	}
	
	@Bean(name = "hibernateDBAccessor")
	public IDBAccessor getDummyAccessor() {
		return new HibernateDbAccessor();
	}
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("org.sqlite.JDBC");
	    dataSource.setUrl("jdbc:sqlite:/Users/neyma/Projects/STS_Workplace/timeTracker/timetracker.db");//jdbc:mysql://localhost:3306/timetracker
	    dataSource.setUsername("");//jdbc:sqlite:D:\\testdb.db
	    dataSource.setPassword("");
	 
	    return dataSource;
	}
	
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 
	    sessionBuilder.addAnnotatedClasses(Status.class);
	    sessionBuilder.addProperties(getHibernateProperties());
	 
	    return sessionBuilder.buildSessionFactory();
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
