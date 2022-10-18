package com.codingjoa.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codingjoa.entity.Member;

@Mapper
public interface MemberMapper {
	
	public void register(Member member);
	
	public boolean isIdExist(String memberId);
	
}
