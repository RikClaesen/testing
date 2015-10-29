package com.example.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.dto.ErrorDetail;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceRefusedException.class)
	@ResponseBody
	protected ResponseEntity<ErrorDetail> handleResourceRefused( ResourceRefusedException ex, 
												//	HttpHeaders headers,
												//	HttpStatus status,	
													WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		//errorDetail.setStatus(status.value());
		errorDetail.setTitle("Message Refused");
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());
			
	//	return handleExceptionInternal(ex, errorDetail, headers, HttpStatus.NOT_FOUND, request);
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
		
	}

}
