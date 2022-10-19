package com.codingjoa.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingjoa.domain.Auth;
import com.codingjoa.domain.Member;
import com.codingjoa.domain.SecurityMember;
import com.codingjoa.dto.JoinDto;
import com.codingjoa.mapper.MemberMapper;
import com.codingjoa.service.MemberService;

import lombok.extern.slf4j.Slf4j;

//@Transactional
@Slf4j
@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void registerMember(JoinDto joinDto) {
		String rawPassword = joinDto.getMemberPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		joinDto.setMemberPassword(encPassword);
		
		Member member = modelMapper.map(joinDto, Member.class);
		log.info("joinDto ==> {}", member);
		
		int result = memberMapper.registerMember(member);
		
		if(result == 1) {
			Auth auth = new Auth();
			auth.setMemberId(member.getMemberId());
			log.info("{}", auth);

			memberMapper.registerAuth(auth);
		}
	}

	@Override
	public boolean isIdExist(String memberId) {
		return memberMapper.isIdExist(memberId);
	}

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		log.info("loadUserByUsername, memberId = {}", memberId);

		SecurityMember securityMember = memberMapper.findSecurityMemberById(memberId);
		log.info("loadUserByUsername, securityMember = {}", securityMember);
		
		if(securityMember == null) {
			throw new UsernameNotFoundException(memberId);
		}
		
		return securityMember;
	}
	
}
