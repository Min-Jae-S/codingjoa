package com.codingjoa.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.security.dto.CustomUserDetails;
import com.codingjoa.security.dto.LoginDto;
import com.codingjoa.security.exception.LoginRequireFieldException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private Validator loginValidator;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("============== CustomAuthenticationProvider ==============");
		
		String memberId = (String) authentication.getPrincipal();
		String memberPassword = (String) authentication.getCredentials();
		LoginDto loginDto = new LoginDto(memberId, memberPassword);
		
		Errors errors = new BeanPropertyBindingResult(loginDto, "loginDto");
		loginValidator.validate(loginDto, errors);
		
		if(errors.hasErrors()) {
			throw new LoginRequireFieldException("login validation failed", errors);
		}
		
		CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(memberId);
		
		if(!passwordEncoder.matches(memberPassword, userDetails.getMemberPassword())) {
			throw new BadCredentialsException(memberId);
		}
		
		return new UsernamePasswordAuthenticationToken(memberId, memberPassword, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
