package com.codingjoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codingjoa.dto.ErrorResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionController {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDTO> errorHandler(MethodArgumentNotValidException e) {
		log.info("-------- MethodArgumentNotValidException --------");
		
		BindingResult result = e.getBindingResult();
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();

		return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}
}
