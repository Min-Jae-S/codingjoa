package com.codingjoa.security.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.codingjoa.mapper.MemberMapper;
import com.codingjoa.security.dto.UserDetailsDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		log.info("loadUserByUsername, memberId = {}", memberId);
		//log.info("'{}' has Text? {}", memberId, StringUtils.hasText(memberId));
		
		Map<String, String> map = memberMapper.findUserDetailsById(memberId);
		log.info("map = {}", map);
		
		if(map == null) {
			throw new UsernameNotFoundException(memberId);
		}
		
		memberId = map.get("memberId");
		
		String memberPassword = map.get("memberPassword");
		String memberRole = map.get("memberRole");
		
		return new UserDetailsDto(memberId, memberPassword, memberRole);
	}
}
