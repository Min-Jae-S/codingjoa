package com.codingjoa.domain;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class MemberVO {

	private int memberIdx;
	private String memberId;
	private String memberPassword;
	private String memberPassword2;
	private String memberEmail;

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
