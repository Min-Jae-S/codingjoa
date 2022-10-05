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

		checkId(memberVO.getMemberId(), errors);
		checkPassword(memberVO.getMemberPassword1(), memberVO.getMemberPassword2(), errors);
		checkEmail(memberVO.getMemberEmail(), errors);

	}

	private void checkId(String memberId, Errors errors) {
		String regexp = "^([a-z0-9]{6,12})$";
		
		if(StringUtils.isEmpty(memberId)) {
			errors.rejectValue("memberId", "NotEmpty");
		} else if (!Pattern.matches(regexp, memberId)) {
			errors.rejectValue("memberId", "Pattern");
		} else if (true /* 아이디 중복확인 */) {
			errors.rejectValue("memberId", "NotCheckVaildId");
		}
	}
	
	private void checkPassword(String memberPassword1, String memberPassword2, Errors errors) {
		String regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}";
		
		if(StringUtils.isEmpty(memberPassword1)) {
			errors.rejectValue("memberPassword1", "NotEmpty");
		} else if (!Pattern.matches(regexp, memberPassword1)) {
			errors.rejectValue("memberPassword1", "Pattern");
		}

		if(StringUtils.isEmpty(memberPassword2)) {
			errors.rejectValue("memberPassword2", "NotEmpty");
		} else if (!Pattern.matches(regexp, memberPassword2)) {
			errors.rejectValue("memberPassword2", "Pattern");
		}
		
		//log.info("memberPassword1 has error? {}", errors.hasFieldErrors("memberPassword1"));
		//log.info("memberPassword2 has error? {}", errors.hasFieldErrors("memberPassword2"));
		
		if(!errors.hasFieldErrors("memberPassword1") && !errors.hasFieldErrors("memberPassword2")) {
			if(!memberPassword1.equals(memberPassword2)) {
				errors.rejectValue("memberPassword1", "NotEquals");
				errors.rejectValue("memberPassword2", "NotEquals");
			}
		}
	}
	
	private void checkEmail(String memberEmail, Errors errors) {
		String regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		
		if(StringUtils.isEmpty(memberEmail)) {
			errors.rejectValue("memberEmail", "NotEmpty");
		} else if (!Pattern.matches(regexp, memberEmail)) {
			errors.rejectValue("memberEmail", "Pattern");
		} else if (true /* 이메일 인증 */) {
			errors.rejectValue("memberEmail", "NotCheckValidEmail");
		}
	}
}
