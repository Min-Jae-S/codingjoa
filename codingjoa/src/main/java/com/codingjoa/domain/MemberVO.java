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

	@NotBlank(groups = NotBlankGroup.class)
	@Size(min = 6, max = 12, groups = SizeGroup.class)
	private String memberId;

	@NotBlank(groups = NotBlankGroup.class)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", 
			 groups = PatternGroup.class)
	private String memberPassword1;

	@NotBlank(groups = NotBlankGroup.class)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",
			 groups = PatternGroup.class)
	private String memberPassword2;

	@NotBlank(groups = NotBlankGroup.class)
	@Email(groups = EmailGroup.class)
	private String memberEmail;

	@NotBlank(groups = NotBlankGroup.class)
	@Pattern(regexp = "^[0-9]{5}$", groups = PatternGroup.class)
	private String memberZipcode;

	@NotBlank(groups = NotBlankGroup.class)
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]$")
	private String memberAddr1;

	@NotBlank(groups = NotBlankGroup.class)
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]$", groups = PatternGroup.class)
	private String memberAddr2;

	@NotBlank(groups = NotBlankGroup.class)
	@Pattern(regexp = "^[YN]{1}$", groups = PatternGroup.class)
	private String memberAgree;
	
	private Date regdate;
	private Date moddate;
}
