package com.codingjoa.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.security.dto.LoginDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("============== LoginValidator ==============");
		
		LoginDto loginDto = (LoginDto) target;
		String memberId = loginDto.getMemberId();
		String memberPassword = loginDto.getMemberPassword();
		
		if("".equals(memberId)) {
			errors.rejectValue("memberId", "NotBlank");
		} else if (!StringUtils.hasText(memberPassword)) {
			errors.rejectValue("memberPassword", "NotBlank");
		}
	}

}
