package com.codingjoa.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.dto.JoinDto;
import com.codingjoa.service.MemberService;
import com.codingjoa.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "joinValidator")
public class JoinValidator implements Validator {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private RedisService redisService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return JoinDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("============== JoinValidator ==============");

		JoinDto joinDto = (JoinDto) target;
		checkId(joinDto.getMemberId(), errors);
		checkPassword(joinDto.getMemberPassword(), joinDto.getConfirmPassword(), errors);
		checkEmailAndAuth(joinDto.getMemberEmail(), joinDto.getAuthCode(), errors);
	}

	private void checkId(String memberId, Errors errors) {
		String regexp = "^([a-z0-9]{6,12})$";

		if (!StringUtils.hasText(memberId)) {
			errors.rejectValue("memberId", "NotBlank");
		} else if (!Pattern.matches(regexp, memberId)) {
			errors.rejectValue("memberId", "Pattern");
		} else if (memberService.isIdExist(memberId)) {
			errors.rejectValue("memberId", "IdExist");
		}
	}

	private void checkPassword(String memberPassword, String confirmPassword, Errors errors) {
		String regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}";

		if (!StringUtils.hasText(memberPassword)) {
			errors.rejectValue("memberPassword", "NotBlank");
		} else if (!Pattern.matches(regexp, memberPassword)) {
			errors.rejectValue("memberPassword", "Pattern");
		}

		if (!StringUtils.hasText(confirmPassword)) {
			errors.rejectValue("confirmPassword", "NotBlank");
		} else if (!Pattern.matches(regexp, confirmPassword)) {
			errors.rejectValue("confirmPassword", "Pattern");
		}

		if (!errors.hasFieldErrors("memberPassword") && !errors.hasFieldErrors("confirmPassword")) {
			if (!memberPassword.equals(confirmPassword)) {
				errors.rejectValue("memberPassword", "NotEquals");
				errors.rejectValue("confirmPassword", "NotEquals");
			}
		}
	}
	
	private void checkEmailAndAuth(String memberEmail, String authCode, Errors errors) {
		String regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		
		if (!StringUtils.hasText(memberEmail)) {
			errors.rejectValue("memberEmail", "NotBlank");
		} else if (!Pattern.matches(regexp, memberEmail)) {
			errors.rejectValue("memberEmail", "Pattern");
		} else if (!StringUtils.hasText(authCode)) {
			errors.rejectValue("authCode", "NotBlank");
		} else if (!redisService.isAuthCodeValid(memberEmail, authCode)) {
			errors.rejectValue("authCode", "NotValid");
		}
	}
	
}
