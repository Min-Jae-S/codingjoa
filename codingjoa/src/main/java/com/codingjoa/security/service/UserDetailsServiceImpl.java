package com.codingjoa.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.codingjoa.entity.Auth;
import com.codingjoa.entity.Member;
import com.codingjoa.security.dto.UserDetailsDto;
import com.codingjoa.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		log.info("loadUserByUsername, memberId = {}", memberId);

		Member member = memberService.findMemberById(memberId);
		log.info("member = {}", member);
		
		Auth auth = memberService.findAuthById(memberId);
		log.info("auth = {}", auth);
		
		if(member == null || auth == null) {
			throw new UsernameNotFoundException(memberId);
		}
		
		return new UserDetailsDto(member, auth.getMemberRole());
	}
}
