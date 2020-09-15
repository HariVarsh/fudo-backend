package com.mindtree.fudo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomerException extends ResponseEntityExceptionHandler
{
	
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid
	(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus status,
			WebRequest request)
	{
		Map<String,Object> result=new HashMap<>();
		result.put("Status Code", HttpStatus.OK.value());
		result.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		result.put("status", "Failure");
		return new ResponseEntity<>(result,HttpStatus.OK);
		
		
	}
	
	
	
	
}
