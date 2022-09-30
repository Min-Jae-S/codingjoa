package com.codingjoa.domain;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.codingjoa.validation.ValidationGroups.EmailGroup;
import com.codingjoa.validation.ValidationGroups.NotBlankGroup;
import com.codingjoa.validation.ValidationGroups.PatternGroup;
import com.codingjoa.validation.ValidationGroups.SizeGroup;

import lombok.Data;

@Data
public class MemberVO {

	private int memberIdx;

	@NotBlank
	@Pattern(regexp = "^[a-z0-9]{6,12}$")
	private String memberId;

	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$")
	private String memberPassword1;

	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$")
	private String memberPassword2;

	@NotBlank
	@Email
	private String memberEmail;

	@NotBlank
	@Pattern(regexp = "^[0-9]{5}$")
	private String memberZipcode;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]$")
	private String memberAddr1;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]$")
	private String memberAddr2;

	@NotBlank
	@Pattern(regexp = "^[YN]{1}$")
	private String memberAgree;
	
	private Date regdate;
	private Date moddate;
}
