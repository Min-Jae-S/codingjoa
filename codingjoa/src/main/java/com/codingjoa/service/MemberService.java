package com.codingjoa.service;

import com.codingjoa.dto.UpdateAddrDto;
import com.codingjoa.dto.UpdateAgreeDto;
import com.codingjoa.dto.JoinDto;
import com.codingjoa.dto.UpdateEmailDto;

public interface MemberService {

	public void register(JoinDto joinDto); // member, auth
	
	public boolean isIdExist(String memberId);
	
	public void updateEmail(UpdateEmailDto updateEmailDto, String memberId);
	
	public void updateAddr(UpdateAddrDto updateAddrDto, String memberId);
	
	public void updateAgree(UpdateAgreeDto updateAddrDto, String memberId);
	
}
