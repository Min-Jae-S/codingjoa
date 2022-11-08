package com.codingjoa.service;

import com.codingjoa.dto.AddrDto;
import com.codingjoa.dto.AgreeDto;
import com.codingjoa.dto.JoinDto;
import com.codingjoa.dto.UpdateEmailDto;

public interface MemberService {

	public void register(JoinDto joinDto); // member, auth
	
	public boolean isIdExist(String memberId);
	
	public void updateEmail(UpdateEmailDto updateEmailDto, String memberId);
	
	public void updateAddr(AddrDto addrDto, String memberId);
	
	public void updateAgree(AgreeDto agreeDto, String memberId);
	
}
