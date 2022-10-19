package com.codingjoa.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SecurityMember implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Collection<GrantedAuthority> memberAuthorities;
	private String memberId;
	private String memberPassword;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return memberAuthorities;
	}

	@Override
	public String getPassword() {
		return memberPassword;
	}

	@Override
	public String getUsername() {
		return memberId;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
