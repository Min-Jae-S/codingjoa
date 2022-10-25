package com.codingjoa.dto;

import lombok.Data;

@Data
public class EmailResponseDto {
	
	private boolean validated;
	private String errorMessage;
	
}
