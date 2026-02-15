package com.spring.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> exception(MethodArgumentNotValidException ex){
	Map<String, String> map=new HashMap<String, String>();
	
	ex.getBindingResult().getFieldErrors().forEach(error->{
		map.put(error.getField(), error.getDefaultMessage());
		});
	return map;
}

}
