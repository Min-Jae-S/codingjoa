package com.codingjoa.domain;

import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class MemberVO {

	private int memberIdx;

	@NotBlank
	@Pattern(regexp = "^([a-z0-9]{6,12})$")
	private String memberId;

	// '숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용
	// (특수문자는 정의된 특수문자만 사용 가능)
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")
	private String memberPassword1;

	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$")
	private String memberPassword2;

	@NotBlank
	@Email
	private String memberEmail;

	@NotBlank
	private String memberZipcode;

	@NotBlank
	private String memberAddr1;

	@NotBlank
	private String memberAddr2;

	@NotBlank
	private String memberAgree;
	
	private Date regdate;
	private Date moddate;
}
