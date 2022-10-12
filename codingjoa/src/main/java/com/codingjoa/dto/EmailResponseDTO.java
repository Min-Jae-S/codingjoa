package com.codingjoa.dto;

import lombok.Data;

@Data
public class EmailResponseDTO {
	
	private boolean validated;
	private String errorMessage;
	
}
