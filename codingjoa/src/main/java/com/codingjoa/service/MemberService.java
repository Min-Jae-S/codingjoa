package com.codingjoa.service;

import com.codingjoa.domain.MemberVO;

public interface MemberService {

	public void register(MemberVO memberVO);
	
	public boolean checkIdExist(String memberId);
	
	
}
