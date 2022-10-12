package com.codingjoa.error;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private int status;
	private String message;
	private String code;
	//private List<FieldError> errors; 
	
}
