package com.codingjoa.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codingjoa.entity.MemberVO;

@Mapper
public interface MemberMapper {
	
	public void register(MemberVO memberVO);
	
	public boolean isIdExist(String memberId);
	
}
