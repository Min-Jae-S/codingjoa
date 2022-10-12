package com.codingjoa.dto;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;

import lombok.Getter;

@Getter
public class ErrorResponse {
	
	private String code;
	private String message;
	
	private ErrorResponse(BindingResult bindingResult, MessageSource messageSource) {
		this.code = bindingResult.getAllErrors().get(0).getCodes()[0];
		this.message = messageSource.getMessage(this.code, null, null);
	}
	
	public static ErrorResponse of(BindingResult bindingResult, MessageSource messageSource) {
		return new ErrorResponse(bindingResult, messageSource);
	}
}
