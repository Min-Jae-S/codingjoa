package com.codingjoa.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	private int memberIdx;
	private String memberId;
	private String memberPassword1;
	private String memberPassword2;
	private String memberEmail;
	private String memberZipcode;
	private String memberAddr1;
	private String memberAddr2;
	private String memberPhone;
	private String memberAgree;
	private Date regdate;
	private Date moddate;
	private Date lastlogin;
}
