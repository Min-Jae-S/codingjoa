package com.codingjoa.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.Errors;

public class LoginRequireFieldException extends AuthenticationException {

	private static final long serialVersionUID = 2417338241376612871L;
	private Errors errors;

	public LoginRequireFieldException(String msg) {
		super(msg);
	}

	public LoginRequireFieldException(String msg, Errors errors) {
		super(msg);
		this.errors = errors;
	}

}
