package com.codingjoa.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.codingjoa.service.EmailService;

@EnableAsync
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Async
	@Override
	public void sendEmail(String memberEmail) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
			// MessagingException
			// MailAuthenticationException, MailSendException, MailException
			try {
				MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
				mailHelper.setTo(memberEmail);
				mailHelper.setSubject("Java Mail Test");
				mailHelper.setText("테스트");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			javaMailSender.send(mimeMessage);
	}

}
