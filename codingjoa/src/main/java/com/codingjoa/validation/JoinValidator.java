package com.codingjoa.validation;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("====================== validate ======================");
		
		MemberVO memberVO = (MemberVO) target;
		String memberId = memberVO.getMemberId();
		
		if(StringUtils.isEmpty(memberId)) {
			errors.rejectValue("memberId", "NotEmpty");
		} else if(!Pattern.matches("^([a-z0-9]{6,12})$", memberId)) {
			errors.rejectValue("memberId", "Pattern"); 
		} else if(true  /* 아이디 중복확인 */) {
			errors.rejectValue("memberId", "DontCheckUserIdExist");
		}
		
		errors.getAllErrors().forEach(e -> {
			log.info(e.getCodes()[0]);
		});
	}

}
