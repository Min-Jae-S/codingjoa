package com.codingjoa.security.exception;

import org.springframework.security.core.AuthenticationException;

import lombok.Getter;

@Getter
public class LoginRequireFieldException extends AuthenticationException {
	
	private static final long serialVersionUID = 2417338241376612871L;
	private String errorCode;
	
	public LoginRequireFieldException(String msg) {
		super(msg);
	}
	
	public LoginRequireFieldException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
