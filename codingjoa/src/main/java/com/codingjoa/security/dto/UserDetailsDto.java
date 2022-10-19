package com.codingjoa.security.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.codingjoa.entity.Auth;
import com.codingjoa.entity.Member;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDetailsDto implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Member member;
	private String memberRole;
	
	public UserDetailsDto(Member member, String memberRole) {
		this.member = member;
		this.memberRole = memberRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new SimpleGrantedAuthority(memberRole));
		
		return collection;
	}

	@Override
	public String getPassword() {
		return member.getMemberPassword();
	}

	@Override
	public String getUsername() {
		return member.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
