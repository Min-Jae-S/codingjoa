package com.codingjoa.security.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.security.dto.LoginDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginValidationFilter implements Filter {
	
	@Autowired
	private Validator loginValidator;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("============== ValidationFilter ==============");
		
		String memberId = request.getParameter("memberId");
		String memberPassword = request.getParameter("memberPassword");
		LoginDto loginDto = new LoginDto(memberId, memberPassword);
		
		Errors errors = new BeanPropertyBindingResult(loginDto, "loginDto");
		loginValidator.validate(loginDto, errors);
		
		if(errors.hasErrors()) {
			errors.getAllErrors().forEach(objectError -> {
				log.info("{}" , objectError.getCodes()[0]);
			});
			
			//throw new AuthenticationRequiredFieldsException("validation failed", errors)
		}
		
		chain.doFilter(request, response);
	}



}
