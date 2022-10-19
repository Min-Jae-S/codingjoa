package com.codingjoa.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codingjoa.entity.Auth;
import com.codingjoa.entity.Member;

@Mapper
public interface MemberMapper {
	
	public int registerMember(Member member);
	
	public void registerAuth(Auth auth);
	
	public boolean isIdExist(String memberId);
	
	public Member findMemberById(String memberId);
	
	public Auth findAuthById(String memberId);
	
	
}
