package com.codingjoa.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	@Autowired
	MessageSource messageSource;
	
	private final String DEFAULT_FAILURE_URL = "/member/login";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("============== LoginFailureHandler ==============");
		log.info("exception = {}", exception.getClass());
		log.info("message = {}", exception.getMessage());
		
		response.sendRedirect(request.getContextPath() + DEFAULT_FAILURE_URL);
	}
	

}
