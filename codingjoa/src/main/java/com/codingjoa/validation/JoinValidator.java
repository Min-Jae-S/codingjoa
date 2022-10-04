package com.codingjoa.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(MemberVO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("====================== JoinValidator ======================");
		log.info("target = {}", target);
		log.info("objectName = {}", errors.getObjectName());
	}

}
