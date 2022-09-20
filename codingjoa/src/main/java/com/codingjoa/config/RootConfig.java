package com.codingjoa.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = "com.codingjoa.service")
@MapperScan(basePackages = "com.codingjoa.mapper")
@PropertySource("/WEB-INF/properties/db.properties")
public class RootConfig {

	@Autowired
	Environment env;
	
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("db.classname"));
		hikariConfig.setJdbcUrl(env.getProperty("db.url"));
		hikariConfig.setUsername(env.getProperty("db.username"));
		hikariConfig.setPassword(env.getProperty("db.password"));
		
		return hikariConfig;
	}
	
	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory factory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		
		return factoryBean.getObject();
	}
}
