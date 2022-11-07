package com.codingjoa.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codingjoa.dto.AddrDto;
import com.codingjoa.dto.AgreeDto;
import com.codingjoa.entity.Auth;
import com.codingjoa.entity.Member;

@Mapper
public interface MemberMapper {
	
	public int registerMember(Member member);
	
	public void registerAuth(Auth auth);
	
	public boolean isIdExist(String memberId);
	
	public Map<String, Object> findUserDetailsById(String memberId); // Member, memberRole
	
	public void updateAddr(@Param("addrDto") AddrDto addrDto, @Param("memberId") String memberId);
	
	public void updateAgree(@Param("addrDto") AgreeDto agreeDto, @Param("memberId") String memberId);
}
