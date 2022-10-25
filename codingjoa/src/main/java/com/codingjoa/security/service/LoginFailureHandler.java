package com.codingjoa.security.service;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.codingjoa.dto.LoginDto;
import com.codingjoa.security.exception.LoginRequireFieldException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		log.info("============== LoginFailureHandler ==============");
		log.info("Exception = {}", e.getClass());
		
		String memberId = request.getParameter("memberId");
		String memberPassword = request.getParameter("memberPassword");
		String errorMessage = null;
		
		if(e instanceof LoginRequireFieldException) {
			String code = ((LoginRequireFieldException) e).getCode();
			errorMessage = messageSource.getMessage(code, null, Locale.getDefault());
		} else if(e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
			errorMessage = messageSource.getMessage("error.UsernameNotFoundOrBadCredentials", null, Locale.getDefault());
		}
		
		LoginDto loginDto = new LoginDto(memberId, memberPassword, errorMessage);
		request.setAttribute("loginDto", loginDto);
		
		request.getRequestDispatcher(request.getContextPath() + "/member/login").forward(request, response);
	}

}
