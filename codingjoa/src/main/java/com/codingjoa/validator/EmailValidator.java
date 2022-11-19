package com.codingjoa.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.dto.EmailDto;
import com.codingjoa.enumclass.EmailType;
import com.codingjoa.security.dto.UserDetailsDto;
import com.codingjoa.service.MemberService;
import com.codingjoa.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "emailValidator")
public class EmailValidator implements Validator {

	private final String EMAIL_REGEXP = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

	@Autowired
	MemberService memberService;

	@Autowired
	RedisService redisService;

	@Override
	public boolean supports(Class<?> clazz) {
		return EmailDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("============== EmailValidator ==============");

		EmailDto emailDto = (EmailDto) target;
		String memberEmail = emailDto.getMemberEmail();
		
		if (!StringUtils.hasText(memberEmail)) {
			errors.rejectValue("memberEmail", "NotBlank");
			return;
		} 
		
		if (!Pattern.matches(EMAIL_REGEXP, memberEmail)) {
			errors.rejectValue("memberEmail", "Pattern");
			return;
		}
		
		EmailType emailType = emailDto.getEmailType();
		
		if (emailType == EmailType.JOIN) {
			if (memberService.isEmailExist(memberEmail)) {
				errors.rejectValue("memberEmail", "EmailExist");
				return;
			}
		} else if (emailType == EmailType.UPDATE) {
			if (memberService.isMyEmail(memberEmail, getCurrentId())) {
				errors.rejectValue("memberEmail", "NotMyEmail");
				return;
			}
			
			if (memberService.isEmailExist(memberEmail)) {
				errors.rejectValue("memberEmail", "EmailExist");
				return;
			}
		} else if (emailType == EmailType.FIND_ACCOUNT) {
			if (!memberService.isEmailExist(memberEmail)) {
				errors.rejectValue("memberEmail", "NotEmailExist");
				return;
			}
		}
	}

	private String getCurrentId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null) return null;

		Object principal = auth.getPrincipal();
		String currentId = null;

		if (principal instanceof UserDetailsDto) {
			UserDetailsDto userDetailsDto = (UserDetailsDto) principal;
			currentId = userDetailsDto.getMember().getMemberId();
		} else if (principal instanceof String) {
			currentId = null; // principal = anonymousUser
		}

		return currentId;
	}

}
