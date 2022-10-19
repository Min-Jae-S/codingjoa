package com.codingjoa.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codingjoa.entity.Auth;
import com.codingjoa.entity.Member;
import com.codingjoa.security.dto.UserDetailsDto;

@Mapper
public interface MemberMapper {
	
	public int registerMember(Member member);
	
	public void registerAuth(Auth auth);
	
	public boolean isIdExist(String memberId);
	
	public UserDetailsDto checkLogin(String memberId);
	
	
}
