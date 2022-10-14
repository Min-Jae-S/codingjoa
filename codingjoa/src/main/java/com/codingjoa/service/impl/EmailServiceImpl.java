package com.codingjoa.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
		String memberEmail = emailRequestDTO.getMemberEmail();
	
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			
			mailHelper.setTo(memberEmail);
			mailHelper.setSubject("Java Mail Test");

			Context context = new Context();
			context.setVariable("authcode", makeAuthCode());
			
			String html = templateEngine.process("template/authcode-mail", context);
			mailHelper.setText(html, true);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// MessagingException에 해당하는 ErrorMessage설정 추가하기
			// Async Config
			e.printStackTrace();
		}
	}
	
	private String makeAuthCode() {
		return "12345x";
	}

}
