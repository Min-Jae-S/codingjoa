package com.codingjoa.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberVO {

	private long memberIdx;
	private String memberId;
	private String memberPassword;
	private String memberPassword2;
	private String memberEmail;
	private String authCode;

	@NotBlank
	private String memberZipcode;
	
	@NotBlank
	private String memberAddr;
	
	@NotBlank
	private String memberAddrDetail;
	
	private boolean memberAgree;
	private Date regdate;
	private Date moddate;
	
}
