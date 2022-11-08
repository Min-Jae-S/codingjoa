package com.codingjoa.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingjoa.dto.AddrDto;
import com.codingjoa.dto.AgreeDto;
import com.codingjoa.dto.JoinDto;
import com.codingjoa.dto.UpdateEmailDto;
import com.codingjoa.entity.Auth;
import com.codingjoa.entity.Member;
import com.codingjoa.mapper.MemberMapper;
import com.codingjoa.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Transactional
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void register(JoinDto joinDto) {
		String rawPassword = joinDto.getMemberPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		joinDto.setMemberPassword(encPassword);
		
		Member member = modelMapper.map(joinDto, Member.class);
		log.info("joinDto ==> {}", member);
		
		int result = memberMapper.registerMember(member);
		
		if(result == 1) {
			Auth auth = new Auth();
			auth.setMemberId(member.getMemberId());
			log.info("auth = {}", auth);
	
			memberMapper.registerAuth(auth);
		}
	}

	@Override
	public boolean isIdExist(String memberId) {
		return memberMapper.isIdExist(memberId);
	}

	@Override
	public void updateEmail(UpdateEmailDto updateEmailDto, String memberId) {
		memberMapper.updateEmail(updateEmailDto, memberId);
	}
	
	@Override
	public void updateAddr(AddrDto addrDto, String memberId) {
		memberMapper.updateAddr(addrDto, memberId);
	}

	@Override
	public void updateAgree(AgreeDto agreeDto, String memberId) {
		memberMapper.updateAgree(agreeDto, memberId);
	}



}
