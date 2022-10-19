package com.codingjoa.service;

import com.codingjoa.dto.JoinDto;

public interface MemberService {

	public void register(JoinDto joinDto); // member, auth
	
	public boolean isIdExist(String memberId);
	
	
}
