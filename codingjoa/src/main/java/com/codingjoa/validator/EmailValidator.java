package com.codingjoa.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.dto.EmailDto;
import com.codingjoa.dto.UpdateEmailDto;
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
		return (EmailDto.class.isAssignableFrom(clazz) || UpdateEmailDto.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("============== EmailValidator ==============");

		String objectName = errors.getObjectName();

		if(objectName.equals("emailDto")) {
			EmailDto emailDto = (EmailDto) target;
			checkEmail(emailDto.getMemberEmail(), errors);
		} else if(objectName.equals("updateEmailDto")) {
			UpdateEmailDto updateEmailDto = (UpdateEmailDto) target;
			checkEmailAndAuth(updateEmailDto.getMemberEmail(), updateEmailDto.getAuthCode(), errors);
		}
	}
	
	private void checkEmail(String memberEmail, Errors errors) {
		if (!StringUtils.hasText(memberEmail)) {
			errors.rejectValue("memberEmail", "NotBlank");
		} else if (!Pattern.matches(EMAIL_REGEXP, memberEmail)) {
			errors.rejectValue("memberEmail", "Pattern");
		} 
	}
	
	private void checkEmailAndAuth(String memberEmail, String authCode, Errors errors) {
		if (!StringUtils.hasText(memberEmail)) {
			errors.rejectValue("memberEmail", "NotBlank");
		} else if (!Pattern.matches(EMAIL_REGEXP, memberEmail)) {
			errors.rejectValue("memberEmail", "Pattern");
		} else if (memberService.isMyEmail(memberEmail, loadMemberId())) {
			errors.rejectValue("memberEmail", "NotMyEmail");
		} else if (memberService.isEmailExist(memberEmail)) {
			errors.rejectValue("memberEmail", "EmailExist");
		} else if (!StringUtils.hasText(authCode)) {
			errors.rejectValue("authCode", "NotBlank");
		} else if (!redisService.isAuthCodeValid(memberEmail, authCode)) {
			errors.rejectValue("authCode", "NotValid");
		}
	}
	
	private String loadMemberId() {
		UserDetailsDto principal = (UserDetailsDto) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		return principal.getMember().getMemberId();
	}
}
