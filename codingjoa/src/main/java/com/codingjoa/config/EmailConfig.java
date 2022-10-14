package com.codingjoa.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("/WEB-INF/properties/mail.properties")
public class EmailConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public JavaMailSender mailSeneder() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("mail.host"));
		mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
		mailSender.setUsername(env.getProperty("mail.username"));
		mailSender.setPassword(env.getProperty("mail.password"));
		mailSender.setJavaMailProperties(javaMailProperties());
		
		return mailSender;
	}
	
	private Properties javaMailProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", env.getProperty("mail.auth"));
		properties.put("mail.smtp.starttls.enable", env.getProperty("mail.enable"));
		
		return properties;
	}
}
