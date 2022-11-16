package com.codingjoa.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingjoa.dto.JoinDto;
import com.codingjoa.dto.UpdateAddrDto;
import com.codingjoa.dto.UpdateAgreeDto;
import com.codingjoa.dto.UpdateEmailDto;
import com.codingjoa.dto.UpdatePasswordDto;
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
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

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
	public boolean isEmailExist(String memberEmail) {
		return memberMapper.isEmailExist(memberEmail);
	}

	@Override
	public void updateEmail(UpdateEmailDto updateEmailDto, String memberId) {
		memberMapper.updateEmail(updateEmailDto.getMemberEmail(), memberId);
	}
	
	@Override
	public boolean isMyEmail(String memberEmail, String memberId) {
		return memberEmail.equals(memberMapper.findEmailById(memberId));
	}
	
	@Override
	public void updateAddr(UpdateAddrDto updateAddrDto, String memberId) {
		memberMapper.updateAddr(updateAddrDto.getMemberZipcode(), 
				updateAddrDto.getMemberAddr(), updateAddrDto.getMemberAddrDetail(), memberId);
	}

	@Override
	public void updateAgree(UpdateAgreeDto updateAgreeDto, String memberId) {
		memberMapper.updateAgree(updateAgreeDto.isMemberAgree(), memberId);
	}

	@Override
	public boolean checkPassword(String memberId, String memberPassword) {
		return passwordEncoder.matches(memberPassword, memberMapper.findPasswordById(memberId));
	}

	@Override
	public void updatePassword(UpdatePasswordDto updatePasswordDto, String memberId) {
		String rawPassword = updatePasswordDto.getMemberPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		
		memberMapper.updatePassword(encPassword, memberId);
	}



}
