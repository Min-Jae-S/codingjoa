package com.codingjoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingjoa.domain.MemberVO;
import com.codingjoa.mapper.MemberMapper;
import com.codingjoa.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Transactional
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void register(MemberVO memberVO) {
		String encodedPassword = passwordEncoder.encode(memberVO.getMemberPassword());
		memberVO.setMemberPassword(encodedPassword);
		
		memberMapper.register(memberVO);
	}

	@Override
	public String checkIdExist(String memberId) {
		return memberMapper.checkIdExist(memberId);
	}
	
}
