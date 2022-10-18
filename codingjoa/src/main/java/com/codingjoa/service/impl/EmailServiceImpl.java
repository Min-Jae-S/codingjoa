package com.codingjoa.service.impl;

import java.time.Duration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.codingjoa.dto.EmailRequestDTO;
import com.codingjoa.service.EmailService;
import com.codingjoa.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAsync
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private RedisService redisService;
	
	@Async // Async Config
	@Override
	public void sendAuthEmail(EmailRequestDTO emailRequestDTO) {
		String authCode = RandomStringUtils.randomAlphanumeric(10);
		log.info("authCode : {}", authCode);

		String html = buildTemplate(authCode);
		String memberEmail = emailRequestDTO.getMemberEmail();
	
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

			mailHelper.setTo(memberEmail);
			mailHelper.setSubject("test");
			mailHelper.setText(html, true);
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		redisService.saveAuthCode(memberEmail, authCode);
	}
	
	private String buildTemplate(String authCode) {
		Context context = new Context();
		context.setVariable("authCode", authCode);
		
		return templateEngine.process("template/authcode-mail", context);
	}

}
