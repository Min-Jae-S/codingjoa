package com.codingjoa.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingjoa.dto.JoinRequestDTO;
import com.codingjoa.entity.MemberVO;
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
		// TODO Auto-generated method stub
		return JoinRequestDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("============== JoinValidator ==============");

		JoinRequestDTO joinRequestDTO = (JoinRequestDTO) target;
		checkId(joinRequestDTO.getMemberId(), errors);
		checkPassword(joinRequestDTO.getMemberPassword(), joinRequestDTO.getMemberPassword2(), errors);
		checkEmailAndAuth(joinRequestDTO.getMemberEmail(), joinRequestDTO.getAuthCode(), errors);
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

	private void checkPassword(String memberPassword, String memberPassword2, Errors errors) {
		String regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}";

		if (!StringUtils.hasText(memberPassword)) {
			errors.rejectValue("memberPassword", "NotBlank");
		} else if (!Pattern.matches(regexp, memberPassword)) {
			errors.rejectValue("memberPassword", "Pattern");
		}

		if (!StringUtils.hasText(memberPassword2)) {
			errors.rejectValue("memberPassword2", "NotBlank");
		} else if (!Pattern.matches(regexp, memberPassword2)) {
			errors.rejectValue("memberPassword2", "Pattern");
		}

		if (!errors.hasFieldErrors("memberPassword") && !errors.hasFieldErrors("memberPassword2")) {
			if (!memberPassword.equals(memberPassword2)) {
				errors.rejectValue("memberPassword", "NotEquals");
				errors.rejectValue("memberPassword2", "NotEquals");
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
