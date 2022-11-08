package com.codingjoa.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codingjoa.dto.UpdateAddrDto;
import com.codingjoa.dto.UpdateAgreeDto;
import com.codingjoa.dto.UpdateEmailDto;
import com.codingjoa.entity.Auth;
import com.codingjoa.entity.Member;

@Mapper
public interface MemberMapper {
	
	public int registerMember(Member member);
	
	public void registerAuth(Auth auth);
	
	public boolean isIdExist(String memberId);
	
	public Map<String, Object> findUserDetailsById(String memberId); // Member, memberRole
	
	public void updateEmail(@Param("updateEmailDto") UpdateEmailDto updateEmailDto, 
							@Param("memberId") String memberId);
	
	public void updateAddr(@Param("updateAddrDto") UpdateAddrDto updateAddrDto, 
							@Param("memberId") String memberId);
	
	public void updateAgree(@Param("updateAgreeDto") UpdateAgreeDto updateAgreeDto, 
							@Param("memberId") String memberId);
}
