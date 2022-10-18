package com.codingjoa.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.dto.JoinDto;
import com.codingjoa.dto.LoginDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "loginValidator")
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return LoginDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("============== LoginValidator ==============");
		
		LoginDto loginDto = (LoginDto) target;
	}

}
