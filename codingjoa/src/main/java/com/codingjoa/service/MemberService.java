package com.codingjoa.service;

import com.codingjoa.entity.MemberVO;

public interface MemberService {

	public void register(MemberVO memberVO);
	
	public boolean isIdExist(String memberId);
	
	
}
