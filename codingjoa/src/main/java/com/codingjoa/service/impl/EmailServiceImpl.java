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
	private RedisTemplate<String, String> redisTemplate;
	
	@Async // Async Config
	@Override
	public void sendAuthEmail(EmailRequestDTO emailRequestDTO) {
		String authCode = RandomStringUtils.randomAlphanumeric(10);
		log.info("authCode : {}", authCode);

		String text = buildTemplate(authCode);
		String memberEmail = emailRequestDTO.getMemberEmail();
	
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

			mailHelper.setTo(memberEmail);
			mailHelper.setSubject("test");
			mailHelper.setText(text, true);
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(memberEmail, authCode, Duration.ofMinutes(5L));
	}
	
	private String buildTemplate(String authCode) {
		Context context = new Context();
		context.setVariable("authCode", authCode);
		
		return templateEngine.process("template/authcode-mail", context);
	}

}
