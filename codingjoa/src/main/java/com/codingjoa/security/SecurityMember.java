package com.codingjoa.security;

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
public class SecurityMember implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Member member;
	private Auth auth;
	
	public SecurityMember(Member member, Auth auth) {
		this.member = member;
		this.auth = auth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new SimpleGrantedAuthority(auth.getMemberRole()));
		
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
