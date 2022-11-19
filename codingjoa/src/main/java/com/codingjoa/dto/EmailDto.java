package com.codingjoa.dto;

import com.codingjoa.enumclass.EmailType;

import lombok.Data;

@Data
public class EmailDto {
	
	private String memberEmail;
	private EmailType emailType;
}
