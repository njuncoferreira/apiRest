package com.example.apiRest.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
	
	@Value("${db.url}")
	String url;
	@Value("${db.username}")
	String username;
	@Value("${db.password}")
	String password;
	@Value("${db.driver-class-name}")
	String driver;
	
	@Bean
	public DataSource hikariDataSource() {
		HikariDataSource config = new HikariDataSource();
		
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.setDriverClassName(driver);
		
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(1);
		config.setPoolName("restApi-db-pool");
		config.setMaxLifetime(600000);
		config.setConnectionTimeout(100000);
		config.setConnectionTestQuery("select 1");
		
		return new HikariDataSource(config);
	}
}
