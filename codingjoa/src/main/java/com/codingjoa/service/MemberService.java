package com.codingjoa.service;

import com.codingjoa.dto.JoinRequestDTO;

public interface MemberService {

	public void register(JoinRequestDTO joinRequestDTO);
	
	public boolean isIdExist(String memberId);
	
	
}
