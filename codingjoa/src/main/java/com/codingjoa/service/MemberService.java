package com.codingjoa.service;

import com.codingjoa.dto.JoinDto;
import com.codingjoa.entity.Auth;
import com.codingjoa.entity.Member;

public interface MemberService {

	public void register(JoinDto joinDto); // member, auth
	
	public boolean isIdExist(String memberId);
	
	public Member findMemberById(String memberId);
	
	public Auth findAuthById(String memberId);
	
}
