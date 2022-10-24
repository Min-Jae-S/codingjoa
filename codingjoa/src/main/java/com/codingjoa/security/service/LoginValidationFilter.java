package com.codingjoa.security.service;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.security.dto.LoginDto;
import com.codingjoa.security.exception.LoginRequireFieldException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginValidationFilter implements Filter {
	
	@Resource(name = "loginValidator")
	private Validator loginValidator;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
										throws IOException, ServletException, AuthenticationException {
		log.info("============== LoginValidationFilter ==============");
		
		String memberId = request.getParameter("memberId");
		String memberPassword = request.getParameter("memberPassword");

		LoginDto loginDto = new LoginDto(memberId, memberPassword);
		Errors errors = new BeanPropertyBindingResult(loginDto, "loginDto");

		loginValidator.validate(loginDto, errors);
		
		if(errors.hasErrors()) {
			throw new LoginRequireFieldException("validation fail", errors);
		}
		
		chain.doFilter(request, response);
	}



}
