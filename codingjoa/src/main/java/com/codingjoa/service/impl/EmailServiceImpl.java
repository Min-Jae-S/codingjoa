package com.codingjoa.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.codingjoa.dto.EmailRequestDTO;
import com.codingjoa.service.EmailService;

@EnableAsync
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Async
	@Override
	public void sendAuthEmail(EmailRequestDTO emailRequestDTO) {
		String subject = "authcode 메일입니다.";
		String to = emailRequestDTO.getMemberEmail();
		String text = buildTemplate();
	
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(text, true);
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			// MessagingException에 해당하는 ErrorMessage설정 추가하기
			// Async Config
			e.printStackTrace();
		}
	}
	
	private String buildTemplate() {
		Context context = new Context();
		context.setVariable("authCode", RandomStringUtils.randomAlphanumeric(10));
		
		return templateEngine.process("template/authcode-mail", context);
	}

}
