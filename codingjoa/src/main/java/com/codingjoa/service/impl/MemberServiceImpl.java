package com.codingjoa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingjoa.domain.MemberVO;
import com.codingjoa.mapper.MemberMapper;
import com.codingjoa.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public List<MemberVO> getMemberList() {
		return memberMapper.getMemberList();
	}
	
	
}