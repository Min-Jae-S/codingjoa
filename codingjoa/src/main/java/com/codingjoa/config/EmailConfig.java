package com.codingjoa.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("/WEB-INF/properties/mail.properties")
public class EmailConfig {

	@Value("${mail.host}")
	private String host;

	@Value("${mail.port}")
	private int port;
	
	@Value("${mail.username}")
	private String username;
	
	@Value("${mail.password}")
	private String password;
	
	@Value("${mail.auth}")
	private boolean auth;
	
	@Value("${mail.enable}")
	private boolean enable;
	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(host);
		javaMailSenderImpl.setPort(port);
		javaMailSenderImpl.setUsername(username);
		javaMailSenderImpl.setPassword(password);
		javaMailSenderImpl.setJavaMailProperties(javaMailProperties());
		
		return javaMailSenderImpl;
	}
	
	private Properties javaMailProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.starttls.enable", enable);
		
		return properties;
	}
}
