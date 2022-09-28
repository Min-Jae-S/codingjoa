package com.codingjoa.domain;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberVO {
	
	private int memberIdx;
	
	@Size(min = 6, max = 12)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String memberId;
	
	@Size(min = 8, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String memberPassword1;
	
	@Size(min = 8, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String memberPassword2;
	
	@NotBlank
	@Email
	private String memberEmail;
	
	@Size(min = 5, max = 5)
	@Pattern(regexp = "[0-9]*")
	private String memberZipcode;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9가-힣]*")
	private String memberAddr1;
	
	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9가-힣]*")
	private String memberAddr2;
	
	@NotBlank
	@Pattern(regexp = "[YN]")
	private String memberAgree;
	
	private Date regdate;
	private Date moddate;
	private Date lastlogin;
}
