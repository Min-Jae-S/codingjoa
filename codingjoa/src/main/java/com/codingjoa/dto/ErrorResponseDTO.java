package com.codingjoa.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {
	
	private String code;
	private String objectName;
	private String message;
}
