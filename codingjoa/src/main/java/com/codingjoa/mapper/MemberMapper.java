package com.codingjoa.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codingjoa.domain.MemberVO;

@Mapper
public interface MemberMapper {
	
	public void register(MemberVO memberVO);
	
}
