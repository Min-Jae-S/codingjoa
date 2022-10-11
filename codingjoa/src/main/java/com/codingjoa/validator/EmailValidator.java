package com.codingjoa.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.domain.MemberVO;
import com.codingjoa.dto.EmailRequestDTO;
import com.codingjoa.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "emailValidator")
public class EmailValidator implements Validator {
	
	@Autowired
	private MemberService memberService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return (EmailRequestDTO.class.isAssignableFrom(clazz) || MemberVO.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("-------- EmailValidator --------");

		String objectName = errors.getObjectName();
		String memberEmail = null;
		
		if(objectName.equals("memberVO")) {
			MemberVO memberVO = (MemberVO) target;
			memberEmail = memberVO.getMemberEmail();
		} else {
			EmailRequestDTO emailRequestDTO = (EmailRequestDTO) target;
			memberEmail = emailRequestDTO.getMemberEmail();
		}
		
		String regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

		if (StringUtils.isEmpty(memberEmail)) {
			errors.rejectValue("memberEmail", "NotEmpty");
		} else if (!Pattern.matches(regexp, memberEmail)) {
			errors.rejectValue("memberEmail", "Pattern");
		}
	}

}
