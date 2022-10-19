package com.codingjoa.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codingjoa.domain.Member;
import com.codingjoa.domain.SecurityMember;

@Mapper
public interface MemberMapper {
	
	public void registerMember(Member member);
	
	public boolean isIdExist(String memberId);
	
	public SecurityMember findSecurityMemberById(String memberId);
	
	
}
