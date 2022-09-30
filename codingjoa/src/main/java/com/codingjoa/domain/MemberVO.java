package com.codingjoa.domain;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.codingjoa.validation.ValidationGroups;

import lombok.Data;

@Data
public class MemberVO {

	private int memberIdx;

	@NotBlank(groups = ValidationGroups.NotBlankGroup.class)
	@Size(min = 6, max = 12)
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", groups = ValidationGroups.PatternGroup.class)
	private String memberId;

	@NotBlank(groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", 
			 groups = ValidationGroups.PatternGroup.class)
	private String memberPassword1;

	@NotBlank(groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",
			 groups = ValidationGroups.PatternGroup.class)
	private String memberPassword2;

	@NotBlank(groups = ValidationGroups.NotBlankGroup.class)
	@Email(groups = ValidationGroups.EmailGroup.class)
	private String memberEmail;

	@NotBlank(groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "^[0-9]{5}$", groups = ValidationGroups.PatternGroup.class)
	private String memberZipcode;

	@NotBlank(groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]$")
	private String memberAddr1;

	@NotBlank(groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]$", groups = ValidationGroups.PatternGroup.class)
	private String memberAddr2;

	@NotBlank(groups = ValidationGroups.NotBlankGroup.class)
	@Pattern(regexp = "^[YN]{1}$", groups = ValidationGroups.PatternGroup.class)
	private String memberAgree;
	
	private Date regdate;
	private Date moddate;
}
