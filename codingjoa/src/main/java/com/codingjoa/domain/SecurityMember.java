package com.codingjoa.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SecurityMember implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String memberId;
	private String memberPassword;
	private String memberRole;
	
	public SecurityMember(String memberId, String memberPassword, String memberRole) {
		this.memberId = memberId;
		this.memberPassword = memberPassword;
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
		return memberPassword;
	}

	@Override
	public String getUsername() {
		return memberId;
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
