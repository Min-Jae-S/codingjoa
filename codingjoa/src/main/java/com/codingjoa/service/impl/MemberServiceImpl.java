package com.codingjoa.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingjoa.dto.JoinRequestDTO;
import com.codingjoa.entity.MemberVO;
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
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void register(JoinRequestDTO joinRequestDTO) {
		String encodedPassword = passwordEncoder.encode(joinRequestDTO.getMemberPassword());
		joinRequestDTO.setMemberPassword(encodedPassword);
		
		MemberVO memberVO = modelMapper.map(joinRequestDTO, MemberVO.class);
		log.info("joinRequestDTO --> memberVO = {}", memberVO);
		
		memberMapper.register(memberVO);
	}

	@Override
	public boolean isIdExist(String memberId) {
		return memberMapper.isIdExist(memberId);
	}
	
}
