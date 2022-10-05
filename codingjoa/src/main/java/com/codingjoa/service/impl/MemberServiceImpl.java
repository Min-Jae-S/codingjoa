package com.codingjoa.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingjoa.domain.MemberVO;
import com.codingjoa.mapper.MemberMapper;
import com.codingjoa.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private MemberMapper memberMapper;
	private PasswordEncoder passwordEncoder;

	@Override
	public void register(MemberVO memberVO) {
		String encodedPassword = passwordEncoder.encode(memberVO.getMemberPassword());
		memberVO.setMemberPassword(encodedPassword);
		
		memberMapper.register(memberVO);
	}
	
}
