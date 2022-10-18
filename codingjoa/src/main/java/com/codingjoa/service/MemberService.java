package com.codingjoa.service;

import com.codingjoa.dto.JoinDto;

public interface MemberService {

	public void register(JoinDto joinDto);
	
	public boolean isIdExist(String memberId);
	
	
}
