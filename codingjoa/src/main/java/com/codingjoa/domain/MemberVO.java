package com.codingjoa.domain;

import java.util.Date;

import lombok.Data;
import lombok.Getter;

@Data
public class MemberVO {
	
	private int memberIdx;
	private String memberEmail;
	private String memberName;
	private String memberPassword;
	private String memberZipcode;
	private String memberAddr1;
	private String memberAddr2;
	private String memberPhone;
	private String memberAgree;
	private Date regdate;
	private Date moddate;
	private Date lastlogin;
}
